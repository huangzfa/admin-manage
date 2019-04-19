package com.duobei.core.operation.activity.service.impl;

import com.duobei.core.operation.activity.dao.ActivityResourceDao;
import com.duobei.core.operation.activity.domain.ActivityResource;
import com.duobei.core.operation.activity.service.ActivityResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description 活动url
 * @date 2019/4/11
 */
@Service("activityResourceService")
public class ActivityResourceServiceImpl implements ActivityResourceService {
    @Autowired
    private ActivityResourceDao resourceDao;

    @Override
    public List<ActivityResource> getListByEnivr(HashMap<String,Object> params){
        return resourceDao.getListByEnivr(params);
    }
}
