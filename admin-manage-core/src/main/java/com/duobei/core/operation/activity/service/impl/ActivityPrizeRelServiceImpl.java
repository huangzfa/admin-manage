package com.duobei.core.operation.activity.service.impl;

import com.duobei.core.operation.activity.dao.ActivityPrizeRelDao;
import com.duobei.core.operation.activity.domain.vo.ActivityPrizeRelVo;
import com.duobei.core.operation.activity.service.ActivityPrizeRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description 活动 活动奖品关联中间表
 * @date 2019/4/11
 */
@Service("activityPrizeRelService")
public class ActivityPrizeRelServiceImpl implements ActivityPrizeRelService {

    @Autowired
    private ActivityPrizeRelDao prizeRelDao;
    /**
     * 查询红包或者转盘活动关联的普通奖品
     * @param params
     * @return
     */
    @Override
    public List<ActivityPrizeRelVo> getByActId(HashMap<String,Object> params){
        return prizeRelDao.getByActId(params);
    }
}
