package com.duobei.console.web.controller.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**逾期提醒
 * @author huangzhongfa
 * @description
 * @date 2019/5/17
 */
@Component
@Lazy(value = false)
@Slf4j
public class QuartzOverdueRemind implements Job {
    @Override
    public void execute(JobExecutionContext context){

    }
}
