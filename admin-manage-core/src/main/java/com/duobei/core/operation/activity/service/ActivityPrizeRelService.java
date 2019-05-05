package com.duobei.core.operation.activity.service;

import com.duobei.core.operation.activity.domain.ActivityPrizeRel;
import com.duobei.core.operation.activity.domain.vo.ActivityPrizeRelVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityPrizeRelService {
    /**
     * 查询红包或者转盘活动关联的普通奖品
     * @param params
     * @return
     */
    List<ActivityPrizeRelVo> getByActId(HashMap<String,Object> params);

    /**
     *删除奖品时查询
     * @param params
     * @return
     */
    List<ActivityPrizeRel> getByPrizeId(HashMap<String,Object> params);
}
