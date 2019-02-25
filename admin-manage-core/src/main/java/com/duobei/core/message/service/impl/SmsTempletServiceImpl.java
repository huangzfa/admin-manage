package com.duobei.core.message.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.dao.SmsTempletDao;
import com.duobei.core.message.dao.mapper.SmsTempletMapper;
import com.duobei.core.message.domain.SmsTemplet;
import com.duobei.core.message.domain.SmsTempletExample;
import com.duobei.core.message.domain.SmsTempletExample.Criteria;
import com.duobei.core.message.domain.criteria.SmsTempletCriteria;
import com.duobei.core.message.service.SmsTempletService;
import com.duobei.exception.TqException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("smsTempletService")
public class SmsTempletServiceImpl implements SmsTempletService {
  private final static String DESC = "短信模板";

  @Autowired
  private SmsTempletDao smsTempletDao;

  @Autowired
  private SmsTempletMapper smsTempletMapper;


  @Override
  public SmsTemplet querySmsTempletById(Integer id) throws TqException {
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    SmsTemplet smsTemplet = smsTempletMapper.selectByPrimaryKey(id);
    if (smsTemplet == null) {
      throw new TqException(DESC + "不存在");
    }
    return smsTemplet;
  }

  @Override
  public void addSmsTemplet(OperatorCredential credential,
      SmsTemplet smsTemplet) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (smsTemplet == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    // 基础校验
    beanValidator(smsTemplet);

    SmsTemplet existSmsTemplet = smsTempletDao.getTempletByTempletCodeAndPlatform(smsTemplet.getTempletCode(),smsTemplet.getPlatform());
    if (existSmsTemplet != null)
      throw new TqException(DESC + "入库失败,改渠道编码已存在");


    smsTemplet.setAddOperatorId(credential.getOpId());
    smsTemplet.setAddTime(new Date());

    // 入库
    if (1 != smsTempletMapper.insertSelective(smsTemplet)) {
      throw new TqException(DESC + "入库失败");
    }
    if (smsTemplet.getId() == null) {
      throw new TqException(DESC + "入库失败");
    }

  }

  @Override
  public void updateSmsTemplet(OperatorCredential credential,
      SmsTemplet smsTemplet) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (smsTemplet == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    if (smsTemplet.getId() == null) {
      throw new TqException(DESC + "id不能为空");
    }
    // 基础校验
    beanValidator(smsTemplet);

    SmsTemplet existSmsTemplet = smsTempletDao.getTempletByTempletCodeAndPlatform(smsTemplet.getTempletCode(),smsTemplet.getPlatform());
    if (existSmsTemplet != null && smsTemplet.getId() != existSmsTemplet.getId())
      throw new TqException(DESC + "入库失败,改渠道编码已存在");

    smsTemplet.setModifyOperatorId(credential.getOpId());
    smsTemplet.setModifyTime(new Date());

    SmsTemplet oldSmsTemplet = querySmsTempletById(smsTemplet.getId());
    if (oldSmsTemplet == null) {
      throw new TqException(DESC + "数据不存在");
    }
    if (1 != smsTempletMapper.updateByPrimaryKeySelective(smsTemplet)) {
      throw new TqException("更新数据库失败");
    }
  }

  @Override
  public void deleteSmsTemplet(OperatorCredential credential, Integer id)
      throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    SmsTemplet smsTemplet = querySmsTempletById(id);
    if (smsTemplet == null) {
      throw new TqException(DESC + "不存在");
    }
    smsTemplet.setModifyOperatorId(credential.getOpId());
    smsTemplet.setModifyTime(new Date());
    smsTemplet.setIsDelete(id);
    if (1 != smsTempletMapper.updateByPrimaryKeySelective(smsTemplet)) {
      throw new TqException("删除失败,更新数据库失败");
    }
  }

  @Override
  public boolean beanValidator(SmsTemplet SmsTemplet) throws TqException {
    if (StringUtils.isBlank(SmsTemplet.getSmsUserfulCode())) {
      throw new TqException("短信类型编码不能为空");
    }
    if (StringUtils.isBlank(SmsTemplet.getTempletCode())) {
      throw new TqException("模板编码不能为空");
    }
    if (StringUtils.isBlank(SmsTemplet.getTempletContent())) {
      throw new TqException("模板内容不能为空");
    }
    if (StringUtils.isBlank(SmsTemplet.getPlatform())) {
      throw new TqException("平台不能为空");
    }
    if (StringUtils.isBlank(SmsTemplet.getDesc())) {
      throw new TqException("模板用途不能为空");
    }
    return true;
  }

  @Override
  public ListVo<SmsTemplet> querySmsTempletList(OperatorCredential credential, SmsTempletCriteria smsTempletCriteria) throws TqException {
      if (credential == null) {
        throw new TqException("操作员不能为空");
      }
      if (smsTempletCriteria == null) {
        throw new TqException("查询条件不能为空");
      }
      SmsTempletExample example = new SmsTempletExample();
      Criteria criteria = example.createCriteria();
      criteria.andIsDeleteEqualTo(0);

      Long total = smsTempletMapper.countByExample(example);
      List<SmsTemplet> smsTemplets= null;
      if (total > 0) {
        smsTemplets = smsTempletDao.querySmsTempletList(smsTempletCriteria);
      }
      return new ListVo<SmsTemplet>(total.intValue(), smsTemplets);
    }

  @Override
  public void changeState(OperatorCredential credential, Integer id, Integer status) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    if (status == null) {
      throw new TqException(DESC + "状态不能为空");
    }
    SmsTemplet smsTemplet = querySmsTempletById(id);
    if (smsTemplet == null) {
      throw new TqException(DESC + "不存在");
    }
    smsTemplet.setModifyOperatorId(credential.getOpId());
    smsTemplet.setModifyTime(new Date());
    smsTemplet.setStatus(status);
    if (1 != smsTempletDao.updateSmsTemplet(smsTemplet)) {
      throw new TqException("更新数据库失败");
    }
  }
}
