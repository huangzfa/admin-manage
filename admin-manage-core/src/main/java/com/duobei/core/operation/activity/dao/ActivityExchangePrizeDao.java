package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/18
 */
public interface ActivityExchangePrizeDao {
    List<ActivityExchangePrizeVo> getByActId(HashMap<String,Object> param)

}
