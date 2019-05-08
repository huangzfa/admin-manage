package com.duobei.core.operation.activity.service.impl;

import com.duobei.core.operation.activity.dao.ActivityTypeDao;
import com.duobei.core.operation.activity.domain.ActivityType;
import com.duobei.core.operation.activity.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 活动类型service
 * @date 2019/4/11
 */
@Service("activityTypeService")
public class ActivityTypeServiceImpl implements ActivityTypeService {

    @Autowired
    private ActivityTypeDao activityTypeDao;

    @Override
    public List<ActivityType> getAll(){
        return activityTypeDao.getAll();
    }
}
