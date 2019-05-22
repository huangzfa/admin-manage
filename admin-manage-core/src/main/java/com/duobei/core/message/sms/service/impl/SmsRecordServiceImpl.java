package com.duobei.core.message.sms.service.impl;

import com.duobei.core.message.sms.dao.SmsRecordDao;
import com.duobei.core.message.sms.domain.SmsRecord;
import com.duobei.core.message.sms.service.SmsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
@Service("smsRecordService")
public class SmsRecordServiceImpl implements SmsRecordService {

    @Autowired
    private SmsRecordDao smsRecordDao;

    @Override
    public void batchSave(HashMap<String,Object> param){
         smsRecordDao.batchSave(param);
    }

    @Override
    public void save(SmsRecord record){
        smsRecordDao.save(record);
    }
}
