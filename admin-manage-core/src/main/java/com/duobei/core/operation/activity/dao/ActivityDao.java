package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.Activity;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityDao {
    List<Activity> getPageList(ActivityCriteria criteria);

    int countByCriteria(ActivityCriteria criteria);

    Activity getByCode(@Param("code") String code);

    int insert(Activity activity);

    int update(Activity activity);
}
