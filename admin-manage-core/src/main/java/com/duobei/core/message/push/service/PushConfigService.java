package com.duobei.core.message.push.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.message.push.domain.PushConfig;
import com.duobei.core.message.push.domain.criteria.PushConfigCriteria;


/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
public interface PushConfigService {

    ListVo<PushConfig> getPage(PushConfigCriteria criteria);

    void save(PushConfig config) throws TqException;

    void update(PushConfig config) throws TqException;

    void editState(PushConfig config) throws TqException ;

    PushConfig getById(Long id);

    int countByAppKey(PushConfig config);

}
