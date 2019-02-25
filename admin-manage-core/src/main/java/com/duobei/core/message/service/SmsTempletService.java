package com.duobei.core.message.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.domain.SmsTemplet;
import com.duobei.core.message.domain.criteria.SmsTempletCriteria;
import com.duobei.exception.TqException;

public interface SmsTempletService {
  SmsTemplet querySmsTempletById(Integer id) throws TqException;

  void addSmsTemplet(OperatorCredential credential, SmsTemplet smsTemplet)
      throws TqException;

  void updateSmsTemplet(OperatorCredential credential, SmsTemplet smsTemplet)
      throws TqException;

  void deleteSmsTemplet(OperatorCredential credential, Integer id) throws TqException;

  boolean beanValidator(SmsTemplet smsTemplet) throws TqException;

  ListVo<SmsTemplet> querySmsTempletList(OperatorCredential credential, SmsTempletCriteria smsTempletCriteria)
      throws TqException;

  void changeState(OperatorCredential credential, Integer id, Integer status) throws TqException;
}
