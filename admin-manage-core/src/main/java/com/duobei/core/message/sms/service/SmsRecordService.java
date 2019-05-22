package com.duobei.core.message.sms.service;

import com.duobei.core.message.sms.domain.SmsRecord;

import java.util.HashMap;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
public interface SmsRecordService {

    void batchSave(HashMap<String,Object> param);

    void save(SmsRecord record);
}
