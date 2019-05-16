package com.duobei.core.message.push.dao;


import com.duobei.core.message.push.domain.PushConfig;
import com.duobei.core.message.push.domain.criteria.PushConfigCriteria;

import java.util.List;

public interface PushConfigDao {

    List<PushConfig> getPageList(PushConfigCriteria criteria);

    int countByCriteria(PushConfigCriteria criteria);

    int save(PushConfig config);

    int update(PushConfig config);

    PushConfig getById(Long id);

    int countByAppKey(PushConfig config);

}