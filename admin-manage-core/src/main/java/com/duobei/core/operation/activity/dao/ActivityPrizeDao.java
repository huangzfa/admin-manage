package com.duobei.core.operation.activity.dao;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.activity.domain.ActivityPrize;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityPrizeDao {

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    List<ActivityPrize> getPageList(ActivityCriteria criteria);

    /**
     * 总条数
     * @param criteria
     * @return
     */
    int countByCriteria(ActivityCriteria criteria);
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

    int save (ActivityPrize record) throws TqException;

    int update (ActivityPrize record) throws TqException;

    ActivityPrize getByPrizeId(@Param("prizeId") Integer prizeId);
}
