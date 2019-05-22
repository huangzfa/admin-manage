package com.duobei.core.message.sms.dao;

import com.duobei.core.message.sms.domain.SmsRecord;

import java.util.HashMap;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
public interface SmsRecordDao {
    void batchSave(HashMap<String,Object> map);

    void save(SmsRecord record);
}
