package com.duobei.core.message.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.domain.JpushTemplet;
import com.duobei.core.message.domain.criteria.JpushTempletCriteria;
import com.duobei.exception.TqException;

public interface JpushTempletService {
  JpushTemplet queryJpushTempletById(Integer id) throws TqException;

  void addJpushTemplet(OperatorCredential credential, JpushTemplet jpushTemplet)
      throws TqException;

  void updateJpushTemplet(OperatorCredential credential, JpushTemplet jpushTemplet)
      throws TqException;

  void deleteJpushTemplet(OperatorCredential credential, Integer id) throws TqException;

  boolean beanValidator(JpushTemplet jpushTemplet) throws TqException;

  ListVo<JpushTemplet> queryJpushTempletList(OperatorCredential credential, JpushTempletCriteria jpushTempletCriteria)
      throws TqException;

  void changeState(OperatorCredential credential, Integer id, Integer status) throws TqException;

}
