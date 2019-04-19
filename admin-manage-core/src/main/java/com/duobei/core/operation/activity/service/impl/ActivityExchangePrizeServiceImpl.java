package com.duobei.core.operation.activity.service.impl;

import com.duobei.core.operation.activity.dao.ActivityExchangePrizeDao;
import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;
import com.duobei.core.operation.activity.service.ActivityExchangePrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/18
 */
@Service("activityExchangePrizeService")
public class ActivityExchangePrizeServiceImpl implements ActivityExchangePrizeService {

    @Autowired
    private ActivityExchangePrizeDao exchangePrizeDao;


    /**
     * 兑换模板活动关联的奖品
     * @param param
     * @return
     */
    @Override
    public List<ActivityExchangePrizeVo> getByActId(HashMap<String,Object> param){
        return exchangePrizeDao.getByActId(param);
    }
}
