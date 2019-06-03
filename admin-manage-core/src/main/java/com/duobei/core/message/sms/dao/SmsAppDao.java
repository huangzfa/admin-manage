package com.duobei.core.message.sms.dao;

import com.duobei.core.message.sms.domain.SmsApp;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/3
 */
public interface SmsAppDao {

    SmsApp getByAppkey(@Param("appkey") String appkey);

    int save(SmsApp smsApp);

    int update();
}
