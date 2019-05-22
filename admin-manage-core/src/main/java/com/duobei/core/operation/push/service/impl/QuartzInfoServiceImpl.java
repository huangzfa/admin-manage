package com.duobei.core.operation.push.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.GuidUtil;
import com.duobei.common.util.QuartzManager;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.push.dao.QuartzInfoDao;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.domain.criteria.QuartzInfoCriteria;
import com.duobei.core.operation.push.service.QuartzInfoService;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/15
 */
@Service("quartzInfoService")
public class QuartzInfoServiceImpl implements QuartzInfoService{

    @Autowired
    private QuartzInfoDao quartzInfoDao;

    private static String    JOB_GROUP_NAME     = "PGY_JOBGROUP_PUSH";

    private static String    TRIGGER_GROUP_NAME = "PGY_TRIGGERGROUP_PUSH";

    private static String    TRIGGER_NAME = "PGY_TRIGGER_PUSH_";

    @Override
    public ListVo<QuartzInfo> getPageList(QuartzInfoCriteria criteria){
        int total = quartzInfoDao.countByCriteria(criteria);
        List<QuartzInfo> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = quartzInfoDao.getPage(criteria);
        }
        return new ListVo<QuartzInfo>(total, list);
    }

    @Override
    public QuartzInfo getByCode(String code){
        return quartzInfoDao.getByCode(code);
    }

    @Override
    public void update(QuartzInfo record) throws Exception{
        QuartzInfo entity = quartzInfoDao.getByCode(record.getCode());
        if( entity == null ){
            throw new TqException("定时任务不存在");
        }
        if(  quartzInfoDao.update(record)<BizConstant.INT_ONE){
           throw new TqException("修改失败");
        }
        if(!StringUtil.isEmpty(record.getCycle()) ){
            if(!entity.getCycle().equals(record.getCycle())){
                //先删除旧任务
                QuartzManager.removeJob(entity.getCode(),entity.getJobGroupName(),entity.getTriggerName(),entity.getTriggerGroupName());
                //添加新任务
                Object cl = Class.forName(record.getClassName()).newInstance();
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("code",entity.getCode());//业务参数
                QuartzManager.addJob(entity.getCode(),jobDataMap,entity.getJobGroupName(),entity.getTriggerName(),entity.getTriggerGroupName(),cl.getClass(),record.getCycle());
            }
        }
    }

    @Override
    public void save(QuartzInfo record) throws Exception{
        record.setJobGroupName(JOB_GROUP_NAME)//任务组组
                .setTriggerGroupName(TRIGGER_GROUP_NAME)//触发器组
                .setTriggerName("TRIGGER_NAME"+ GuidUtil.getSerialNumber());//触发器名
        if(  quartzInfoDao.save(record)<BizConstant.INT_ONE){
            throw new TqException("添加失败");
        }
        //添加任务到定时器
        Object cl = Class.forName(record.getClassName()).newInstance();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("code",record.getCode());
        QuartzManager.addJob(record.getCode(),jobDataMap,record.getJobGroupName(),record.getTriggerName(),record.getTriggerGroupName(),cl.getClass(),record.getCycle());

    }

    @Override
    public void delete(QuartzInfo record) throws Exception{
        if(  quartzInfoDao.update(record)<BizConstant.INT_ONE){
            throw new TqException("删除失败");
        }
        QuartzManager.removeJob(record.getCode(),record.getJobGroupName(),record.getTriggerName(),record.getTriggerGroupName());
    }

    @Override
    public void editState(QuartzInfo record) throws Exception{
        if(  quartzInfoDao.update(record)<BizConstant.INT_ONE){
            throw new TqException("操作失败");
        }
    }

    @Override
    public List<QuartzInfo> getStart(){
        return quartzInfoDao.getStart();
    }
}
