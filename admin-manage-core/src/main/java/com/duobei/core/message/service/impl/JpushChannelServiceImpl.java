package com.duobei.core.message.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.dao.JpushChannelDao;
import com.duobei.core.message.dao.mapper.JpushChannelMapper;
import com.duobei.core.message.domain.JpushChannel;
import com.duobei.core.message.domain.JpushChannelExample;
import com.duobei.core.message.domain.JpushChannelExample.Criteria;
import com.duobei.core.message.domain.criteria.JpushChannelCriteria;
import com.duobei.core.message.service.JpushChannelService;
import com.duobei.exception.TqException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jpushChannelService")
public class JpushChannelServiceImpl implements JpushChannelService {
  private final static String DESC = "极光推送渠道";

  @Autowired
  private JpushChannelDao jpushChannelDao;

  @Autowired
  private JpushChannelMapper jpushChannelMapper;


  @Override
  public JpushChannel queryJpushChannelById(Integer id) throws TqException {
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    JpushChannel jpushChannel = jpushChannelMapper.selectByPrimaryKey(id);
    if (jpushChannel == null) {
      throw new TqException(DESC + "不存在");
    }
    return jpushChannel;
  }

  @Override
  public void addJpushChannel(OperatorCredential credential,
      JpushChannel jpushChannel) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (jpushChannel == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    // 基础校验
    beanValidator(jpushChannel);

    JpushChannel existJpushChannel = jpushChannelDao.getChannelByChannelCodeAndPlatform(jpushChannel.getJpushChannelCode(),jpushChannel.getPlatform());
    if (existJpushChannel != null)
      throw new TqException(DESC + "入库失败,该渠道编码已存在");


    jpushChannel.setAddOperatorId(credential.getOpId());
    jpushChannel.setAddTime(new Date());

    // 入库
    if (1 != jpushChannelMapper.insertSelective(jpushChannel)) {
      throw new TqException(DESC + "入库失败");
    }
  }

  @Override
  public void updateJpushChannel(OperatorCredential credential,
      JpushChannel jpushChannel) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (jpushChannel == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    if (jpushChannel.getId() == null) {
      throw new TqException(DESC + "id不能为空");
    }
    // 基础校验
    beanValidator(jpushChannel);

    JpushChannel existJpushChannel = jpushChannelDao.getChannelByChannelCodeAndPlatform(jpushChannel.getJpushChannelCode(),jpushChannel.getPlatform());
    if (existJpushChannel != null && jpushChannel.getId() != existJpushChannel.getId())
      throw new TqException(DESC + "入库失败,该渠道编码已存在");

    jpushChannel.setModifyOperatorId(credential.getOpId());
    jpushChannel.setModifyTime(new Date());

    JpushChannel oldJpushChannel = queryJpushChannelById(jpushChannel.getId());
    if (oldJpushChannel == null) {
      throw new TqException(DESC + "数据不存在");
    }
    if (1 != jpushChannelMapper.updateByPrimaryKeySelective(jpushChannel)) {
      throw new TqException("更新数据库失败");
    }
  }

  @Override
  public void deleteJpushChannel(OperatorCredential credential, Integer id)
      throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    JpushChannel jpushChannel = queryJpushChannelById(id);
    if (jpushChannel == null) {
      throw new TqException(DESC + "不存在");
    }
    jpushChannel.setModifyOperatorId(credential.getOpId());
    jpushChannel.setModifyTime(new Date());
    jpushChannel.setIsDelete(id);
    if (1 != jpushChannelMapper.updateByPrimaryKeySelective(jpushChannel)) {
      throw new TqException("删除失败,更新数据库失败");
    }
  }

  @Override
  public boolean beanValidator(JpushChannel jpushChannel) throws TqException {
      if (StringUtils.isBlank(jpushChannel.getPlatform())) {
        throw new TqException("平台不能为空");
      }
      if (StringUtils.isBlank(jpushChannel.getJpushChannelCode())) {
        throw new TqException("极光渠道编码不能为空");
      }
      if (StringUtils.isBlank(jpushChannel.getJpushChannelName())) {
        throw new TqException("极光渠道名称不能为空");
      }
      if (StringUtils.isBlank(jpushChannel.getSecret())) {
        throw new TqException("极光Secret不能为空");
      }
      return true;
    }

  @Override
  public ListVo<JpushChannel> queryJpushChannelList(OperatorCredential credential, JpushChannelCriteria jpushChannelCriteria) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (jpushChannelCriteria == null) {
      throw new TqException("查询条件不能为空");
    }
    JpushChannelExample example = new JpushChannelExample();
    Criteria criteria = example.createCriteria();
    criteria.andIsDeleteEqualTo(0);

    Long total = jpushChannelMapper.countByExample(example);
    List<JpushChannel> jpushChannels= null;
    if (total > 0) {
      jpushChannels = jpushChannelDao.queryJpushChannelList(jpushChannelCriteria);
    }
    return new ListVo<JpushChannel>(total.intValue(), jpushChannels);
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
    JpushChannel jpushChannel = queryJpushChannelById(id);
    if (jpushChannel == null) {
      throw new TqException(DESC + "不存在");
    }
    jpushChannel.setModifyOperatorId(credential.getOpId());
    jpushChannel.setModifyTime(new Date());
    jpushChannel.setStatus(status);
    if (1 != jpushChannelMapper.updateByPrimaryKeySelective(jpushChannel)) {
      throw new TqException("更新数据库失败");
    }
  }
}
