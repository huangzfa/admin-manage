package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.ActivityExchange;
import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/12
 */
public interface ActivityExchangeDao {
    ActivityExchange getById(Integer id);

    int insert(ActivityExchange exchange);

    int update(ActivityExchange exchange);


}
