package com.duobei.core.message.sms.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import com.duobei.core.message.sms.domain.criteria.SmsAppChannelConfigCriteria;
import com.duobei.core.message.sms.domain.vo.SmsAppChannelConfigVo;
import com.duobei.core.operation.app.domain.App;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
public interface SmsAppChannelConfigService {

    ListVo<SmsAppChannelConfigVo> getPage(SmsAppChannelConfigCriteria criteria,List<App> appList);

    void save(SmsAppChannelConfig config) throws TqException;

    void update(SmsAppChannelConfig config) throws TqException;

    void editState(SmsAppChannelConfig config) throws TqException;

    List<SmsAppChannelConfig> getByAppKey(String appKey);

    /**
     * 推送时查询是否配置了应用
     * @param config
     * @return
     */
    int countByAppKey(SmsAppChannelConfig config);

    SmsAppChannelConfig getByRecord(SmsAppChannelConfig config);

    SmsAppChannelConfig getById(Long id);
}
