package com.duobei.core.operation.activity.service.impl;

import com.duobei.core.operation.activity.dao.ActivityExchangeDao;
import com.duobei.core.operation.activity.domain.ActivityExchange;
import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;
import com.duobei.core.operation.activity.service.ActivityExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/12
 */
@Service("activityExchangeService")
public class ActivityExchangeServiceImpl implements ActivityExchangeService {

    @Autowired
    private ActivityExchangeDao exchangeDao;

    @Override
    public ActivityExchange getById(Integer id){
        return exchangeDao.getById(id);
    }

}
