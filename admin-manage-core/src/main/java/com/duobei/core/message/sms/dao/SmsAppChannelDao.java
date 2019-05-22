package com.duobei.core.message.sms.dao;

import com.duobei.core.message.sms.domain.SmsAppChannel;


/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
public interface SmsAppChannelDao {

   void save(SmsAppChannel channel);

   void update(SmsAppChannel channel);

   SmsAppChannel getByRecord(SmsAppChannel channel);
}
