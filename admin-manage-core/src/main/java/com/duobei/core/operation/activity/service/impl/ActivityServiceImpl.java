package com.duobei.core.operation.activity.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.activity.dao.*;
import com.duobei.core.operation.activity.domain.*;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 活动表service
 * @date 2019/4/11
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityPrizeRelDao activityPrizeRelDao;
    @Autowired
    private ActivityHongbaoPrizeDao hongbaoPrizeDao;
    @Autowired
    private ActivityStaticDao activityStaticDao;
    @Autowired
    private ActivityExchangeDao activityExchangeDao;
    @Autowired
    private ActivityExchangePrizeDao exchangePrizeDao;
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<Activity> getLists(ActivityCriteria criteria){
        int total = activityDao.countByCriteria(criteria);
        List<Activity> appPages = null;
        if (total > BizConstant.INT_ZERO) {
            appPages = activityDao.getPageList(criteria);
        }
        return new ListVo<Activity>(total , appPages);
    }

    /**
     *
     * @param code
     * @return
     */
    @Override
    public Activity getByCode(String code){
        return  activityDao.getByCode(code);
    }

    /**
     *
     * @param record
     * @param prizeRelList
     * @param prizeHongbaoList
     * @throws TqException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void saveHongbao(Activity record, List<ActivityPrizeRel> prizeRelList, List<ActivityHongbaoPrize> prizeHongbaoList) throws TqException{
        if( record.getActId() == null ){
            if(activityDao.insert(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
        }else{
            if( activityDao.update(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
            //将旧的活动奖品删除
            activityPrizeRelDao.batchDelete(record.getActId());
            hongbaoPrizeDao.batchDelete(record.getActId());
        }
        for (ActivityPrizeRel rel : prizeRelList){
            //根据有就添加，无责更新原则
            if( rel.getActId().equals(BizConstant.INT_ZERO)){
                activityPrizeRelDao.insert(rel);
            }else{
                activityPrizeRelDao.update(rel);
            }
        }
        for (ActivityHongbaoPrize rel : prizeHongbaoList){
            //根据有就添加，无责更新原则
            if( rel.getActId().equals(BizConstant.INT_ZERO)){
                hongbaoPrizeDao.insert(rel);
            }else{
                hongbaoPrizeDao.update(rel);
            }
        }
    }

    /**
     *
     * @param record
     * @param prizeRelList
     * @throws TqException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void saveZhuanpan(Activity record,List<ActivityPrizeRel> prizeRelList) throws TqException{
        if( record.getActId() == null ){
            if(activityDao.insert(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
        }else{
            if( activityDao.update(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
            //将旧的活动奖品删除
            hongbaoPrizeDao.batchDelete(record.getActId());
        }
        for (ActivityPrizeRel rel : prizeRelList){
            //根据有就添加，无责更新原则
            if( rel.getActId().equals(BizConstant.INT_ZERO)){
                activityPrizeRelDao.insert(rel);
            }else{
                activityPrizeRelDao.update(rel);
            }
        }
    }

    /**
     *静态模板保存
     * @param record
     * @param activityStatic
     * @throws TqException
     */
    @Override
    public void saveStatic(Activity record, ActivityStatic activityStatic) throws TqException{
        if( record.getActId() == null ){
            if(activityDao.insert(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
            activityStaticDao.insert(activityStatic);
        }else{
            if( activityDao.update(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
            activityStaticDao.update(activityStatic);
        }
    }

    @Override
    public void saveExchange(Activity record, List<ActivityExchangePrize> prizeExchangeList,ActivityExchange activityExchange) throws TqException{
        if( record.getActId() == null ){
            if(activityDao.insert(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
            activityExchangeDao.insert(activityExchange);
        }else{
            if( activityDao.update(record) < BizConstant.INT_ONE){
                throw new TqException("保存失败");
            }
            activityExchangeDao.update(activityExchange);
            exchangePrizeDao.batchDelete(record.getActId());
        }
        for (ActivityExchangePrize rel : prizeExchangeList){
            //根据有就添加，无责更新原则
            if( rel.getActId().equals(BizConstant.INT_ZERO)){
                exchangePrizeDao.insert(rel);
            }else{
                exchangePrizeDao.update(rel);
            }
        }
    }
}
