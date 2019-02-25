package com.duobei.core.message.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.domain.SmsChannel;
import com.duobei.core.message.domain.criteria.SmsChannelCriteria;
import com.duobei.core.message.vo.SmsStyleVo;
import com.duobei.exception.TqException;
import java.util.List;

public interface SmsChannelService {

  SmsChannel querySmsChannelById(Integer id) throws TqException;

  void addSmsChannel(OperatorCredential credential, SmsChannel smsChannel)
      throws TqException;

  void updateSmsChannel(OperatorCredential credential, SmsChannel smsChannel)
      throws TqException;

  void deleteSmsChannel(OperatorCredential credential, Integer id) throws TqException;

  boolean beanValidator(SmsChannel smsChannel) throws TqException;

  ListVo<SmsChannel> querySmsChannelList(OperatorCredential credential, SmsChannelCriteria smsChannelCriteria)
      throws TqException;

  List<SmsStyleVo> getSmsStyleVo();

  void changeState(OperatorCredential credential, Integer id, Integer status) throws TqException;
}
