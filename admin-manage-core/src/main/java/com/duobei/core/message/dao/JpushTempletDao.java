package com.duobei.core.message.dao;

import com.duobei.core.message.domain.JpushTemplet;
import com.duobei.core.message.domain.criteria.JpushTempletCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JpushTempletDao {

  List<JpushTemplet> queryJpushTempletList(JpushTempletCriteria jpushTempletCriteria);

  JpushTemplet getTempletByTempletCodeAndPlatform(@Param("templetCode") String templetCode, @Param("platform") String platform);
}