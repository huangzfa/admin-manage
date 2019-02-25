package com.duobei.core.message.dao;

import com.duobei.core.message.domain.SmsTemplet;
import com.duobei.core.message.domain.criteria.SmsTempletCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsTempletDao {

  List<SmsTemplet> querySmsTempletList(SmsTempletCriteria smsTempletCriteria);

  SmsTemplet getTempletByTempletCodeAndPlatform(@Param("templetCode")String templetCode, @Param("platform")String platform);

  int updateSmsTemplet(SmsTemplet smsTemplet);

}