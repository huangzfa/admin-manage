package com.duobei.core.operation.activity.service;

import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/18
 */
public interface ActivityExchangePrizeService {
    /**
     * 兑换模板活动关联的奖品
     * @param param
     * @return
     */
    List<ActivityExchangePrizeVo> getByActId(HashMap<String,Object> param);
}
