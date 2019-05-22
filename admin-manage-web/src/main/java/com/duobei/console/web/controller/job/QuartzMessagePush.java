package com.duobei.console.web.controller.job;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.util.BeanUtil;
import com.duobei.common.vo.ReturnParamsVo;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.service.PushRecordService;
import com.duobei.core.operation.push.service.QuartzInfoService;
import com.duobei.dic.ZD;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/15
 */
@Component
@Lazy(value = false)
@Slf4j
public class QuartzMessagePush implements Job{

    @Override
    public void execute(JobExecutionContext context){
        log.info("定时任务执行开始.........");
        //获取定时任务参数
        JobDataMap paramMap = context.getJobDetail().getJobDataMap();
        if( paramMap!=null && paramMap.size()> BizConstant.INT_ZERO){
            String code = paramMap.getString("code");
            QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
            //查询定时任务
            QuartzInfo quartzInfo = quartzInfoService.getByCode(code);
            if( quartzInfo!=null && quartzInfo.getState().equals(BizConstant.INT_ONE)){
                //查询推送业务
                PushRecordService recordService = (PushRecordService) BeanUtil.getBean("pushRecordService");
                PushRecord record = recordService.getById(Long.parseLong(code.substring(code.lastIndexOf("_")+1,code.length())));
                if( record!=null ){
                    if(record.getPushType().equals(ZD.pushType_jg)){
                        recordService.jgPush(record,null);
                    }else{
                        recordService.smsPush(record,null);
                    }
                }
                QuartzInfo entity = new QuartzInfo()
                        .setId(quartzInfo.getId())
                        .setCode(quartzInfo.getCode())
                        .setSucceed(BizConstant.INT_ONE);
                try {
                    quartzInfoService.update(entity);
                }catch (Exception e){
                    log.error(entity.getName()+"定时任务更新失败："+e.getMessage());
                }
            }
        }
    }
}
