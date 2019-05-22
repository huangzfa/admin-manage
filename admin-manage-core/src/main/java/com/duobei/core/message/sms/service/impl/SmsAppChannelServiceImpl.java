package com.duobei.core.message.sms.service.impl;

import com.duobei.core.message.sms.dao.SmsAppChannelDao;
import com.duobei.core.message.sms.service.SmsAppChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
@Slf4j
@Service("smsAppChannelService")
public class SmsAppChannelServiceImpl implements SmsAppChannelService {

    @Autowired
    private SmsAppChannelDao appChannelDao;


}
