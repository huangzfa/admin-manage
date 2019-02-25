package com.duobei.core.message.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.domain.JpushChannel;
import com.duobei.core.message.domain.criteria.JpushChannelCriteria;
import com.duobei.exception.TqException;

public interface JpushChannelService {
  JpushChannel queryJpushChannelById(Integer id) throws TqException;

  void addJpushChannel(OperatorCredential credential, JpushChannel jpushChannel)
      throws TqException;

  void updateJpushChannel(OperatorCredential credential, JpushChannel jpushChannel)
      throws TqException;

  void deleteJpushChannel(OperatorCredential credential, Integer id) throws TqException;

  boolean beanValidator(JpushChannel jpushChannel) throws TqException;

  ListVo<JpushChannel> queryJpushChannelList(OperatorCredential credential, JpushChannelCriteria jpushChannelCriteria)
      throws TqException;

  void changeState(OperatorCredential credential, Integer id, Integer status) throws TqException;
}
