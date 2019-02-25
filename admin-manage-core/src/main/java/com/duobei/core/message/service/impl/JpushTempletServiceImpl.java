package com.duobei.core.message.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.dao.JpushTempletDao;
import com.duobei.core.message.dao.mapper.JpushTempletMapper;
import com.duobei.core.message.domain.JpushTemplet;
import com.duobei.core.message.domain.JpushTempletExample;
import com.duobei.core.message.domain.JpushTempletExample.Criteria;
import com.duobei.core.message.domain.criteria.JpushTempletCriteria;
import com.duobei.core.message.service.JpushTempletService;
import com.duobei.exception.TqException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jpushTempletService")
public class JpushTempletServiceImpl implements JpushTempletService {
  private final static String DESC = "极光推送模板";

  @Autowired
  private JpushTempletDao jpushTempletDao;

  @Autowired
  private JpushTempletMapper jpushTempletMapper;



  @Override
  public JpushTemplet queryJpushTempletById(Integer id) throws TqException {
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    JpushTemplet jpushTemplet = jpushTempletMapper.selectByPrimaryKey(id);
    if (jpushTemplet == null) {
      throw new TqException(DESC + "不存在");
    }
    return jpushTemplet;
  }

  @Override
  public void addJpushTemplet(OperatorCredential credential,
      JpushTemplet jpushTemplet) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (jpushTemplet == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    // 基础校验
    beanValidator(jpushTemplet);

    JpushTemplet existJpushTemplet = jpushTempletDao.getTempletByTempletCodeAndPlatform(jpushTemplet.getTempletCode(),jpushTemplet.getPlatform());
    if (existJpushTemplet != null)
      throw new TqException(DESC + "入库失败,改渠道编码已存在");


    jpushTemplet.setAddOperatorId(credential.getOpId());
    jpushTemplet.setAddTime(new Date());

    // 入库
    if (1 != jpushTempletMapper.insertSelective(jpushTemplet)) {
      throw new TqException(DESC + "入库失败");
    }

  }

  @Override
  public void updateJpushTemplet(OperatorCredential credential,
      JpushTemplet jpushTemplet) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (jpushTemplet == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    if (jpushTemplet.getId() == null) {
      throw new TqException(DESC + "id不能为空");
    }
    // 基础校验
    beanValidator(jpushTemplet);

    JpushTemplet existJpushTemplet = jpushTempletDao.getTempletByTempletCodeAndPlatform(jpushTemplet.getTempletCode(),jpushTemplet.getPlatform());
    if (existJpushTemplet != null && existJpushTemplet.getId() != jpushTemplet.getId())
      throw new TqException(DESC + "入库失败,改渠道编码已存在");

    jpushTemplet.setModifyOperatorId(credential.getOpId());
    jpushTemplet.setModifyTime(new Date());

    JpushTemplet oldJpushTemplet = queryJpushTempletById(jpushTemplet.getId());
    if (oldJpushTemplet == null) {
      throw new TqException(DESC + "数据不存在");
    }
    if (1 != jpushTempletMapper.updateByPrimaryKeySelective(jpushTemplet)) {
      throw new TqException("更新数据库失败");
    }
  }

  @Override
  public void deleteJpushTemplet(OperatorCredential credential, Integer id)
      throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    JpushTemplet jpushTemplet = queryJpushTempletById(id);
    if (jpushTemplet == null) {
      throw new TqException(DESC + "不存在");
    }
    jpushTemplet.setModifyOperatorId(credential.getOpId());
    jpushTemplet.setModifyTime(new Date());
    jpushTemplet.setIsDelete(id);
    if (1 != jpushTempletMapper.updateByPrimaryKeySelective(jpushTemplet)) {
      throw new TqException("删除失败,更新数据库失败");
    }
  }

  @Override
  public boolean beanValidator(JpushTemplet jpushTemplet) throws TqException {
    if (StringUtils.isBlank(jpushTemplet.getTempletCode())) {
      throw new TqException("模板类型编码不能为空");
    }
    if (StringUtils.isBlank(jpushTemplet.getTempletContent())) {
      throw new TqException("模板标题不能为空");
    }
    if (StringUtils.isBlank(jpushTemplet.getTempletParams())) {
      throw new TqException("模板替换值不能为空");
    }
    if (StringUtils.isBlank(jpushTemplet.getSendType())) {
      throw new TqException("发送平台不能为空");
    }
    return true;
  }

  @Override
  public ListVo<JpushTemplet> queryJpushTempletList(OperatorCredential credential, JpushTempletCriteria jpushTempletCriteria) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (jpushTempletCriteria == null) {
      throw new TqException("查询条件不能为空");
    }
    JpushTempletExample example = new JpushTempletExample();
    Criteria criteria = example.createCriteria();
    criteria.andIsDeleteEqualTo(0);

    Long total = jpushTempletMapper.countByExample(example);
    List<JpushTemplet> jpushTemplets= null;
    if (total > 0) {
      jpushTemplets = jpushTempletDao.queryJpushTempletList(jpushTempletCriteria);
    }
    return new ListVo<JpushTemplet>(total.intValue(), jpushTemplets);
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
    JpushTemplet jpushTemplet = queryJpushTempletById(id);
    if (jpushTemplet == null) {
      throw new TqException(DESC + "不存在");
    }
    jpushTemplet.setModifyOperatorId(credential.getOpId());
    jpushTemplet.setModifyTime(new Date());
    jpushTemplet.setStatus(status);
    if (1 != jpushTempletMapper.updateByPrimaryKeySelective(jpushTemplet)) {
      throw new TqException("更新数据库失败");
    }
  }

}
