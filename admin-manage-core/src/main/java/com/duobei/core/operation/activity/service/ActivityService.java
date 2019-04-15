package com.duobei.core.operation.activity.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.activity.domain.Activity;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityService {
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    ListVo<Activity> getLists(ActivityCriteria criteria);

    /**
     *
     * @param code
     * @return
     */
    Activity getByCode(String code);
}
