package com.duobei.core.operation.activity.service.impl;

import com.duobei.core.operation.activity.dao.ActivityStaticDao;
import com.duobei.core.operation.activity.domain.ActivityStatic;
import com.duobei.core.operation.activity.service.ActivityStaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/12
 */
@Service("activityStaticService")
public class ActivityStaticServiceImpl implements ActivityStaticService {

    @Autowired
    private ActivityStaticDao staticDao;

    @Override
    public ActivityStatic getById(Integer id){
        return staticDao.getById(id);
    }
}
