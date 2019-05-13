package com.duobei.core.operation.activity.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.activity.dao.ActivityPrizeDao;
import com.duobei.core.operation.activity.domain.ActivityPrize;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.service.ActivityPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description 活动奖品service
 * @date 2019/4/11
 */
@Service("activityPrizeService")
public class ActivityPrizeServiceImpl implements ActivityPrizeService {

    @Autowired
    private ActivityPrizeDao activityPrizeDao;

    /**
     *分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<ActivityPrize> getPageList(ActivityCriteria criteria){
        int total = activityPrizeDao.countByCriteria(criteria);
        List<ActivityPrize> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = activityPrizeDao.getPageList(criteria);
        }
        return new ListVo<ActivityPrize>(total , list);
    }

    /**
     * //自由品类和不中奖奖品
     * @param params
     * @return
     */
    @Override
    public List<ActivityPrize> getByActId(HashMap<String,Object> params){
        return activityPrizeDao.getByActId(params);
    }

    /**
     * 优惠券和借款券
     * @param params
     * @return
     */
    @Override
    public List<ActivityPrize> getCouponByActId(HashMap<String,Object> params){
        return activityPrizeDao.getCouponByActId(params);
    }

    /**
     *
     * @param record
     * @throws TqException
     */
    @Override
    public void save (ActivityPrize record) throws TqException{
        if( activityPrizeDao.save(record) <1){
            throw new TqException("添加失败");
        }
    }

    /**
     *
     * @param record
     * @throws TqException
     */
    @Override
    public void update (ActivityPrize record) throws TqException{
        if( activityPrizeDao.update(record) <1){
            throw new TqException("修改失败");
        }
    }

    @Override
    public ActivityPrize getByPrizeId(Integer actId){
        return activityPrizeDao.getByPrizeId(actId);
    }
}
