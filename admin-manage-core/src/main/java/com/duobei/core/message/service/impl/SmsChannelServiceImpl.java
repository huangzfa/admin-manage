package com.duobei.core.message.service.impl;

import com.duobei.common.enums.SmsChannelStatusEnums;
import com.duobei.common.enums.SmsUserfulCodeEnums;
import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.dao.SmsChannelDao;
import com.duobei.core.message.dao.mapper.SmsChannelMapper;
import com.duobei.core.message.domain.SmsChannel;
import com.duobei.core.message.domain.SmsChannelExample;
import com.duobei.core.message.domain.SmsChannelExample.Criteria;
import com.duobei.core.message.domain.criteria.SmsChannelCriteria;
import com.duobei.core.message.service.SmsChannelService;
import com.duobei.core.message.vo.SmsStyleVo;
import com.duobei.exception.TqException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("smsChannelService")
public class SmsChannelServiceImpl implements SmsChannelService {

  private final static String DESC = "短信渠道";

  @Autowired
  private SmsChannelDao smsChannelDao;

  @Autowired
  private SmsChannelMapper smsChannelMapper;


  @Override
  public SmsChannel querySmsChannelById(Integer id) throws TqException {
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }

    SmsChannel smsChannel = smsChannelMapper.selectByPrimaryKey(id);
    if (smsChannel == null) {
      throw new TqException(DESC + "不存在");
    }
    return smsChannel;
  }

  @Override
  public void addSmsChannel(OperatorCredential credential, SmsChannel smsChannel) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (smsChannel == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    // 基础校验
    beanValidator(smsChannel);

    SmsChannel existSmsChannel = smsChannelDao.getChannelByChannelCodeAndPlatform(smsChannel.getSmsChannelCode(),smsChannel.getPlatform());
    if (existSmsChannel != null)
      throw new TqException(DESC + "入库失败,该渠道编码已存在");


    smsChannel.setAddOperatorId(credential.getOpId());
    smsChannel.setAddTime(new Date());
    String smsUserfulCode = smsChannel.getSmsUserfulCode();
    if (smsUserfulCode.equals(SmsUserfulCodeEnums.NORMAL.getCode())){
      smsChannel.setSmsUserfulName(SmsUserfulCodeEnums.NORMAL.getMsg());
    }else if (smsUserfulCode.equals(SmsUserfulCodeEnums.COLLECTION.getCode())){
      smsChannel.setSmsUserfulName(SmsUserfulCodeEnums.COLLECTION.getMsg());
    }else if (smsUserfulCode.equals(SmsUserfulCodeEnums.MARKETING.getCode())){
      smsChannel.setSmsUserfulName(SmsUserfulCodeEnums.MARKETING.getMsg());
    }else {
      throw new TqException(DESC + "入库失败,未知短信类型");
    }

    // 入库
    if (1 != smsChannelMapper.insertSelective(smsChannel)) {
      throw new TqException(DESC + "入库失败");
    }

  }

  @Override
  public void updateSmsChannel(OperatorCredential credential,
      SmsChannel smsChannel) throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (smsChannel == null) {
      throw new TqException(DESC + "数据不能为空");
    }
    if (smsChannel.getId() == null) {
      throw new TqException(DESC + "id不能为空");
    }
    // 基础校验
    beanValidator(smsChannel);

    SmsChannel existSmsChannel = smsChannelDao.getChannelByChannelCodeAndPlatform(smsChannel.getSmsChannelCode(),smsChannel.getPlatform());
    if (existSmsChannel != null && existSmsChannel.getId() != smsChannel.getId())
      throw new TqException(DESC + "入库失败,该渠道编码已存在");

    smsChannel.setModifyOperatorId(credential.getOpId());
    smsChannel.setModifyTime(new Date());
    String smsUserfulCode = smsChannel.getSmsUserfulCode();
    if (smsUserfulCode.equals(SmsUserfulCodeEnums.NORMAL.getCode())){
      smsChannel.setSmsUserfulName(SmsUserfulCodeEnums.NORMAL.getMsg());
    }else if (smsUserfulCode.equals(SmsUserfulCodeEnums.COLLECTION.getCode())){
      smsChannel.setSmsUserfulName(SmsUserfulCodeEnums.COLLECTION.getMsg());
    }else if (smsUserfulCode.equals(SmsUserfulCodeEnums.MARKETING.getCode())){
      smsChannel.setSmsUserfulName(SmsUserfulCodeEnums.MARKETING.getMsg());
    }else {
      throw new TqException(DESC + "入库失败,未知短信类型");
    }

    SmsChannel oldSmsChannel = querySmsChannelById(smsChannel.getId());
    if (oldSmsChannel == null) {
      throw new TqException(DESC + "数据不存在");
    }
    if (1 != smsChannelMapper.updateByPrimaryKeySelective(smsChannel)) {
      throw new TqException("更新数据库失败");
    }
  }

  @Override
  public void deleteSmsChannel(OperatorCredential credential, Integer id)
      throws TqException {
    if (credential == null) {
      throw new TqException("操作员不能为空");
    }
    if (id == null) {
      throw new TqException(DESC + "id不能为空");
    }
    SmsChannel smsChannel = querySmsChannelById(id);
    if (smsChannel == null) {
      throw new TqException(DESC + "不存在");
    }
    Integer status = smsChannel.getStatus();
    if (status == SmsChannelStatusEnums.USE.getCode()){
      throw new TqException("渠道启用中无法删除");
    }
    smsChannel.setModifyOperatorId(credential.getOpId());
    smsChannel.setModifyTime(new Date());
    smsChannel.setIsDelete(id);
    if (1 != smsChannelMapper.updateByPrimaryKeySelective(smsChannel)) {
      throw new TqException("删除失败,更新数据库失败");
    }
  }

  @Override
  public boolean beanValidator(SmsChannel smsChannel) throws TqException {
    if (StringUtils.isBlank(smsChannel.getPlatform())) {
      throw new TqException("平台不能为空");
    }
    if (StringUtils.isBlank(smsChannel.getSmsUserfulCode())) {
      throw new TqException("短信类型编码不能为空");
    }
    if (StringUtils.isBlank(smsChannel.getSmsChannelCode())) {
      throw new TqException("短信渠道编码不能为空");
    }
    if (StringUtils.isBlank(smsChannel.getSmsChannelName())) {
      throw new TqException("短信渠道名称不能为空");
    }
    if (StringUtils.isBlank(smsChannel.getSmsSign())) {
      throw new TqException("短信签名不能为空");
    }
    if (StringUtils.isBlank(smsChannel.getRequestUrl())) {
      throw new TqException("请求地址不能为空");
    }
    if (StringUtils.isBlank(smsChannel.getAccount())) {
      throw new TqException("账号不能为空");
    }
    if (StringUtils.isBlank(smsChannel.getPassword())) {
      throw new TqException("密码不能为空");
    }
    return true;
  }

  @Override
  public ListVo<SmsChannel> querySmsChannelList(OperatorCredential credential, SmsChannelCriteria smsChannelCriteria)throws TqException {
      if (credential == null) {
        throw new TqException("操作员不能为空");
      }
      if (smsChannelCriteria == null) {
        throw new TqException("查询条件不能为空");
      }
      SmsChannelExample example = new SmsChannelExample();
      Criteria criteria = example.createCriteria();
      criteria.andIsDeleteEqualTo(0);

      Long total = smsChannelMapper.countByExample(example);
      List<SmsChannel> smsChannels= null;
      if (total > 0) {
        smsChannels = smsChannelDao.querySmsChannelList(smsChannelCriteria);
      }
      return new ListVo<SmsChannel>(total.intValue(), smsChannels);
  }

  @Override
  public List<SmsStyleVo> getSmsStyleVo() {
    return smsChannelDao.getSmsStyleVo();
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
    SmsChannel smsChannel = querySmsChannelById(id);
    if (smsChannel == null) {
      throw new TqException(DESC + "不存在");
    }
    List<SmsChannel> smsChannels = smsChannelDao.getByPlatformAndUserfulCodeAndStatus(smsChannel.getPlatform(), smsChannel.getSmsUserfulCode(), SmsChannelStatusEnums.USE.getCode());
    if (status == SmsChannelStatusEnums.FORBID.getCode() && smsChannel.getStatus() == SmsChannelStatusEnums.USE.getCode() &&smsChannels.size() ==1){
      throw new TqException("该平台同类型渠道不能完全禁用");
    }
    smsChannel.setModifyOperatorId(credential.getOpId());
    smsChannel.setModifyTime(new Date());
    smsChannel.setStatus(status);
    if (1 != smsChannelMapper.updateByPrimaryKeySelective(smsChannel)) {
      throw new TqException("更新数据库失败");
    }
  }

}
