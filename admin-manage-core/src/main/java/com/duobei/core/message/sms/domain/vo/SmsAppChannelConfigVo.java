package com.duobei.core.message.sms.domain.vo;

import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
@Data
public class SmsAppChannelConfigVo extends SmsAppChannelConfig {
    private String channelCodeName;

    private String appName;
}
