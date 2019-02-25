package com.duobei.core.message.dao;


import com.duobei.core.message.domain.JpushChannel;
import com.duobei.core.message.domain.criteria.JpushChannelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JpushChannelDao {

  List<JpushChannel> queryJpushChannelList(JpushChannelCriteria jpushChannelCriteria);

  JpushChannel getChannelByChannelCodeAndPlatform(@Param("jpushChannelCode") String jpushChannelCode, @Param("platform")String platform);
}