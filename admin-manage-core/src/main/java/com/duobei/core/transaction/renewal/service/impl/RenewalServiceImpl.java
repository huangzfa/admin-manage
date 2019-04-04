package com.duobei.core.transaction.renewal.service.impl;

import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.borrow.dao.BorrowCashDao;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.renewal.dao.BorrowCashRenewalDao;
import com.duobei.core.transaction.renewal.dao.mapper.BorrowCashRenewalMapper;
import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;
import com.duobei.core.transaction.renewal.domain.BorrowCashRenewalExample;
import com.duobei.core.transaction.renewal.domain.criteria.BorrowCashRenewalCriteria;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalListVo;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalVo;
import com.duobei.core.transaction.renewal.service.RenewalService;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.pgy.data.handler.PgyDataHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
@Service("renewalService")
public class RenewalServiceImpl implements RenewalService {

    @Resource
    BorrowCashRenewalDao renewalDao;

    @Resource
    BorrowCashRenewalMapper renewalMapper;

    @Resource
    BorrowCashDao borrowCashDao;

    @Resource
    UserDao userDao;

    @Resource
    ProductDao productDao;
    @Override
    public ListVo<BorrowCashRenewalListVo> getListByQuery(BorrowCashRenewalCriteria renewalCriteria) {
        BorrowCashRenewalExample example = new BorrowCashRenewalExample();
        BorrowCashRenewalExample.Criteria criteria = example.createCriteria();
        //赋值基础查询条件
        criteria.andIsDeleteEqualTo(0L);
        criteria.andProductIdEqualTo(renewalCriteria.getProductId());
        //查询产品信息
        Product product = productDao.getById(renewalCriteria.getProductId());
        Long total = 0L;
        List<BorrowCashRenewalListVo> data = null;
        if (StringUtil.isNotEmpty(renewalCriteria.getBorrowNo()) && StringUtil.isEmpty(renewalCriteria.getUserName())){
            //借款表查询条件存在，用户表查询条件不存在
            //查询借款表
            List<Long>  borrowIds = borrowCashDao.getIdByBorrowNo(renewalCriteria.getBorrowNo());
            if (borrowIds != null && borrowIds.size() > 0 ){
                criteria.andBorrowCashIdIn(borrowIds);
                total = renewalMapper.countByExample(example);
                if (total > 0) {
                    renewalCriteria.setBorrowIds(borrowIds);
                    //查询续借列表
                    data = renewalDao.getListVoByQuery(renewalCriteria);
                    //查询用户
                    Map<Long,UserAndIdCardVo> userMap = userDao.getUserAndIdCardMapByRenelwalUserIds(data);
                    //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                    for (BorrowCashRenewalListVo vo : data){
                        UserAndIdCardVo userInfo = userMap.get(vo.getUserId());
                        if (userInfo != null){
                            vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                            vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));
                        }
                        vo.setBorrowNo(renewalCriteria.getBorrowNo());
                        vo.setProductName(product.getProductName());

                    }
                }
            }
        }else if (StringUtil.isNotEmpty(renewalCriteria.getUserName()) && StringUtil.isEmpty(renewalCriteria.getBorrowNo())){
            //用户表查询条件存在，借款表查询条件不存在
            UserAndIdCardVo userInfo = userDao.getUserByMobileAndProductId(PgyDataHandler.md5(renewalCriteria.getUserName()),renewalCriteria.getProductId());
            if (userInfo != null){
                criteria.andUserIdEqualTo(userInfo.getId());
                total = renewalMapper.countByExample(example);
                if (total > 0){
                    //查询续借列表
                    data = renewalDao.getListVoByQuery(renewalCriteria);
                    //查询借款信息
                    Map<Long,BorrowCash> borrowCashMap = borrowCashDao.getBorrowCashMapByRenewalBorrowIds(data);
                    //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                    for (BorrowCashRenewalListVo vo : data){
                        BorrowCash borrowCash = borrowCashMap.get(vo.getBorrowCashId());
                        if (borrowCash != null) {
                            vo.setBorrowNo(borrowCash.getBorrowNo());
                        }
                        vo.setProductName(product.getProductName());
                        vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                        vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));


                    }
                }

            }
        }else if (StringUtil.isNotEmpty(renewalCriteria.getUserName()) && StringUtil.isNotEmpty(renewalCriteria.getBorrowNo())){
            //三表查询
            //优先查询用户表
            UserAndIdCardVo userInfo = userDao.getUserByMobileAndProductId(PgyDataHandler.md5(renewalCriteria.getUserName()),renewalCriteria.getProductId());
            //查询借款表
            if (userInfo != null ){
                List<Long>  borrowIds = borrowCashDao.getIdByBorrowNoAndUserId(renewalCriteria.getBorrowNo(),userInfo.getId());
                if ( borrowIds != null && borrowIds.size() > 0){
                    criteria.andBorrowCashIdIn(borrowIds);
                    criteria.andUserIdEqualTo(userInfo.getId());
                    total = renewalMapper.countByExample(example);
                    if (total > 0){
                        //查询续借列表
                        data = renewalDao.getListVoByQuery(renewalCriteria);
                        //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                        for (BorrowCashRenewalListVo vo : data){
                            vo.setBorrowNo(renewalCriteria.getBorrowNo());
                            vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                            vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));
                            vo.setProductName(product.getProductName());
                        }
                    }
                }
            }
        }else{
            //不存在其他表条件查询时
            total = renewalMapper.countByExample(example);
            if (total > 0) {
                //查询续借列表
                data = renewalDao.getListVoByQuery(renewalCriteria);
                //查询用户
                Map<Long, UserAndIdCardVo> userMap = userDao.getUserAndIdCardMapByRenelwalUserIds(data);
                //查询借款信息
                Map<Long, BorrowCash> borrowCashMap = borrowCashDao.getBorrowCashMapByRenewalBorrowIds(data);
                //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                for (BorrowCashRenewalListVo vo : data) {
                    BorrowCash borrowCash = borrowCashMap.get(vo.getBorrowCashId());
                    UserAndIdCardVo userInfo = userMap.get(vo.getUserId());
                    if (borrowCash != null) {
                        vo.setBorrowNo(borrowCash.getBorrowNo());
                    }
                    if (userInfo != null){
                        vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                        vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));
                    }
                    vo.setProductName(product.getProductName());
                }
            }
        }
        return new ListVo(total.intValue(),data);
    }

    @Override
    public BorrowCashRenewalVo getById(Long id) {
        BorrowCashRenewalVo vo = new BorrowCashRenewalVo();
        //查询还款信息
        BorrowCashRenewal borrowCashRenewal = renewalDao.getById(id);
        if (borrowCashRenewal != null){
            //查询借款信息
            BorrowCash borrowCash = borrowCashDao.getById(borrowCashRenewal.getBorrowCashId());
            //赋值
            BeanUtils.copyProperties(borrowCashRenewal,vo) ;
            vo.setBorrowCash(borrowCash);

        }
        return vo;
    }
}
