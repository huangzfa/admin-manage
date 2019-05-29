package com.duobei.core.operation.activity.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.activity.domain.*;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityService {
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    ListVo<Activity> getLists(ActivityCriteria criteria);

    /**
     *
     * @param code
     * @return
     */
    Activity getByCode(String code);

    /**
     *
     * @param record
     * @param prizeRelList
     * @param prizeHongbaoList
     * @throws TqException
     */
    void saveHongbao(Activity record, List<ActivityPrizeRel> prizeRelList, List<ActivityHongbaoPrize> prizeHongbaoList) throws TqException;

    /**
     *
     * @param record
     * @param prizeRelList
     * @throws TqException
     */
    void saveZhuanpan(Activity record,List<ActivityPrizeRel> prizeRelList) throws TqException;

    /**
     *
     * @param record
     * @param activityStatic
     * @throws TqException
     */
    void saveStatic(Activity record, ActivityStatic activityStatic) throws TqException;

    /**
     *
     * @param record
     * @param activityExchange
     * @throws TqException
     */
    void saveExchange(Activity record, List<ActivityExchangePrize> prizeExchangeList,ActivityExchange activityExchange) throws TqException;
}
