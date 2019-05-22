package com.duobei.core.message.sms.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.message.sms.dao.SmsTempletDao;
import com.duobei.core.message.sms.domain.SmsTemplet;
import com.duobei.core.message.sms.domain.criteria.SmsTempletCriteria;
import com.duobei.core.message.sms.domain.vo.SmsTempletVo;
import com.duobei.core.message.sms.service.SmsTempletService;
import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
@Service("smsTempletService")
public class SmsTempletServiceImpl implements SmsTempletService {

    @Autowired
    private SmsTempletDao templetDao;

    @Override
    public ListVo<SmsTempletVo> getPage(SmsTempletCriteria criteria){
        int total = templetDao.countByCriteria(criteria);
        List<SmsTempletVo> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = templetDao.getPageList(criteria);
            for( SmsTempletVo vo : list){
                for (App app : criteria.getAppList()){
                    if(vo.getAppKey().equals(app.getAppKey())){
                        vo.setAppName(app.getAppName());
                        break;
                    }
                }
            }
        }
        return new ListVo<SmsTempletVo>(total, list);
    }

    @Override
    public void save(SmsTemplet record) throws TqException{
        SmsTemplet smsTemplet = new SmsTemplet()
                .setAppKey(record.getAppKey())
                .setCode(record.getCode());
        if( templetDao.countByType(smsTemplet) >BizConstant.INT_ZERO){
            throw new TqException("请勿添加重复类型短信");
        }
        if( templetDao.update(record) < BizConstant.INT_ONE){
            throw new TqException("添加失败");
        }
    }

    @Override
    public void update(SmsTemplet record) throws TqException{
        SmsTemplet smsTemplet = new SmsTemplet()
                .setAppKey(record.getAppKey())
                .setCode(record.getCode())
                .setId(record.getId());
        if( templetDao.countByType(smsTemplet) >BizConstant.INT_ZERO){
            throw new TqException("请勿添加重复类型短信");
        }
        if( templetDao.update(record) < BizConstant.INT_ONE){
            throw new TqException("更新失败");
        }
    }

    @Override
    public SmsTemplet getById(Long id){
        return templetDao.getById(id);
    }

    @Override
    public SmsTemplet getByCode(String code){
        return templetDao.getByCode(code);
    }
}
