package com.duobei.core.message.dao;

import com.duobei.core.message.domain.SmsChannel;
import com.duobei.core.message.domain.criteria.SmsChannelCriteria;
import com.duobei.core.message.vo.SmsStyleVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsChannelDao {

  List<SmsChannel> querySmsChannelList(SmsChannelCriteria smsChannelCriteria);

  SmsChannel getChannelByChannelCodeAndPlatform(@Param("smsChannelCode") String smsChannelCode, @Param("platform") String platform);

  List<SmsStyleVo> getSmsStyleVo();

  List<SmsChannel> getByPlatformAndUserfulCodeAndStatus(@Param("platform")String platform, @Param("smsUserfulCode")String smsUserfulCode,@Param("status")int status);

}