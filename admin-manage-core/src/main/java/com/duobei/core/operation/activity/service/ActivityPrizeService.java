package com.duobei.core.operation.activity.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.activity.domain.ActivityPrize;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityPrizeService {

    /**
     *分页查询
     * @param criteria
     * @return
     */
    ListVo<ActivityPrize> getPageList(ActivityCriteria criteria);

    /**
     * //其他品类和不中奖奖品
     * @param params
     * @return
     */
    List<ActivityPrize> getByActId(HashMap<String,Object> params);

    /**
     * 优惠券和借款券
     * @param params
     * @return
     */
    List<ActivityPrize> getCouponByActId(HashMap<String,Object> params);

    void save (ActivityPrize record) throws TqException;

    void update (ActivityPrize record) throws TqException;

    ActivityPrize getByPrizeId(Integer actId);
}
