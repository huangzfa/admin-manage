package com.duobei.core.operation.zfb.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.BeanUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.zfb.dao.ZfbAccountDao;
import com.duobei.core.operation.zfb.dao.ZfbAccountGuideDao;
import com.duobei.core.operation.zfb.dao.mapper.ZfbAccountMapper;
import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.operation.zfb.domain.ZfbAccountExample;
import com.duobei.core.operation.zfb.domain.ZfbAccountGuide;
import com.duobei.core.operation.zfb.domain.criteria.ZfbAccountCriteria;
import com.duobei.core.operation.zfb.domain.vo.ZfbAccountVo;
import com.duobei.core.operation.zfb.service.ZfbAccountService;
import com.duobei.dic.ZD;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/12
 */
@Service(value = "zfbAccountService")
public class ZfbAccountServiceImpl implements ZfbAccountService {

    @Resource
    ZfbAccountMapper zfbAccountMapper;

    @Resource
    ZfbAccountDao zfbAccountDao;

    @Resource
    ZfbAccountGuideDao zfbAccountGuideDao;

    @Override
    public ListVo<ZfbAccount> queryZfbAccountList(ZfbAccountCriteria zfbAccountCriteria) {
        ZfbAccountExample example = new ZfbAccountExample();
        ZfbAccountExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andProductIdEqualTo(zfbAccountCriteria.getProductId());
        Long total = zfbAccountMapper.countByExample(example);
        //判断总条数是否>0
        List<ZfbAccount> data = null;
        if (total > 0){
            data = zfbAccountDao.getListByQuery(zfbAccountCriteria);
        }
        return new ListVo<>(total.intValue(),data);
    }

    @Override
    public ZfbAccount queryZfbAccountById(Integer id) {
        return zfbAccountDao.getById(id);
    }

    @Override
    public void save(ZfbAccountVo zfbAccountVo) throws TqException {
        //先判断数据库中该产品启用的支付宝账号是否>0
        int count = zfbAccountDao.countByIsEnableAndProductId(zfbAccountVo.getProductId(),ZD.isEnable_yes);
        if (count < 1){
            //初始第一个账号为开启
            zfbAccountVo.setIsEnable(ZD.isEnable_yes);
        }else{
            //之后新增都为禁用
            zfbAccountVo.setIsEnable(ZD.isEnable_no);
        }
        //先新增支付宝账号信息
        count = zfbAccountDao.save(zfbAccountVo);
        if (count != 1){
            throw new TqException("新增失败");
        }
        //再新增付宝教程图片信息
        addZfbAccountGuide(zfbAccountVo);
    }

    private void addZfbAccountGuide(ZfbAccountVo zfbAccountVo) throws TqException {
        //判断是否为修改
        if (zfbAccountVo.getAddTime() == null) {
            //没有新增时间，则是修改
            //先将原该账号支付宝教程图片删除
            zfbAccountGuideDao.deleteOldGuideByAccount(zfbAccountVo);
        }
            //数据处理
            String[] split = zfbAccountVo.getImgUrls().split(",");
            List<ZfbAccountGuide> guideList = new ArrayList<>();
            byte sort = 1;
            for (String imgUrl : split) {
                ZfbAccountGuide zfbAccountGuide = new ZfbAccountGuide();
                zfbAccountGuide.setImgUrl(imgUrl);
                zfbAccountGuide.setZfbAccountId(zfbAccountVo.getId());
                zfbAccountGuide.setImgSort(sort);
                zfbAccountGuide.setAddOperatorId(zfbAccountVo.getModifyOperatorId());
                zfbAccountGuide.setAddTime(zfbAccountVo.getModifyTime());
                zfbAccountGuide.setModifyOperatorId(zfbAccountVo.getModifyOperatorId());
                zfbAccountGuide.setModifyTime(zfbAccountVo.getModifyTime());
                sort++;
                guideList.add(zfbAccountGuide);
            }
            //新增数据
           int count = zfbAccountGuideDao.saveList(guideList);
            if (count != split.length){
                throw new TqException("支付宝教程编辑失败");
            }
    }

    @Override
    public void update(ZfbAccountVo zfbAccountVo) throws TqException {
        //先修改支付宝账号信息
        int count = zfbAccountDao.update(zfbAccountVo);
        if (count != 1){
            throw new TqException("新增失败");
        }
        addZfbAccountGuide(zfbAccountVo);
        //再修改支付宝教程图片信息
    }

    @Override
    public void deleteZfbAccount(ZfbAccount zfbAccount) throws TqException {
        //删除支付宝账号
        int count = zfbAccountDao.delete(zfbAccount);
        if (count != 1){
            throw new TqException("删除失败");
        }
        //删除支付宝教程图片
        zfbAccountGuideDao.deleteOldGuideByAccount(zfbAccount);
    }

    @Override
    public void updateStatus(ZfbAccount zfbAccount) throws TqException {
        //修改状态时，如果为禁用，则查询改产品目前启用的支付宝账号数量为多少
        int count;
        if (ZD.isEnable_no == zfbAccount.getIsEnable()){
            count = zfbAccountDao.countByIsEnableAndProductId(zfbAccount.getProductId(),ZD.isEnable_yes);
            if (count <= 1){
                throw new TqException("禁用失败，支付宝账号必须有一个为开启状态");
            }
        }
         count = zfbAccountDao.updateStatus(zfbAccount);
        if (count != 1){
            throw new TqException("修改状态失败") ;
        }
    }

}
