package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.ActivityStatic;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/12
 */
public interface ActivityStaticDao {
    ActivityStatic getById(Integer id);

    int update(ActivityStatic activityStatic);

    int insert(ActivityStatic activityStatic);
}
