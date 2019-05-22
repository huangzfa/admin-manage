package com.duobei.core.message.sms.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.message.sms.domain.SmsTemplet;
import com.duobei.core.message.sms.domain.criteria.SmsTempletCriteria;
import com.duobei.core.message.sms.domain.vo.SmsTempletVo;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
public interface SmsTempletService {

    ListVo<SmsTempletVo> getPage(SmsTempletCriteria criteria);

    void save(SmsTemplet record) throws TqException;

    void update(SmsTemplet record) throws TqException;

    SmsTemplet getById(Long id);

    SmsTemplet getByCode(String code);
}
