package com.duobei.core.operation.activity.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.activity.dao.ActivityDao;
import com.duobei.core.operation.activity.domain.Activity;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 活动表service
 * @date 2019/4/11
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<Activity> getLists(ActivityCriteria criteria){
        int total = activityDao.countByCriteria(criteria);
        List<Activity> appPages = null;
        if (total > BizConstant.INT_ZERO) {
            appPages = activityDao.getPageList(criteria);
        }
        return new ListVo<Activity>(total , appPages);
    }

    /**
     *
     * @param code
     * @return
     */
    @Override
    public Activity getByCode(String code){
        return  activityDao.getByCode(code);
    }
}
