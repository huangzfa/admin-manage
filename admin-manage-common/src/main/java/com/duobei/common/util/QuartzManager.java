package com.duobei.common.util;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 
 *@类描述：任务管理
 *@author 何鑫 2017年1月18日  12:51:33
 *@注意：本内容仅限于杭州霖梓网络科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@SuppressWarnings({ "rawtypes" })
public class QuartzManager {

    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

    // private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String    JOB_GROUP_NAME     = "EXTJWEB_JOBGROUP_NAME";
    private static String    TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
    
    /**
     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     * @param jobName 任务名
     * @param cls 任务
     * @param time 时间设置，参考quartz说明文档
     */
    @SuppressWarnings("unchecked")
    public static void addJob(String jobName, Map<String, Object> paramMap, Class cls, String time) {
        try {
            JobDetail jobDetail = newJob(cls).withIdentity(jobName,JOB_GROUP_NAME)
                    .usingJobData("code", paramMap.get("code").toString())
                    .build(); // 任务名，任务组，任务执行类
            //构造任务触发器
            Trigger trigger = newTrigger()
                    .withIdentity(jobName, TRIGGER_GROUP_NAME)
                    .withSchedule(cronSchedule(time))// 触发器时间设定
                    .build();
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 添加一个定时任务
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass 任务
     * @param time 时间设置，参考quartz说明文档
     */
    @SuppressWarnings("unchecked")
    public static void addJob(String jobName, Map<String, Object> paramMap, String jobGroupName, String triggerName,
                              String triggerGroupName, Class  jobClass, String time) {
        try {
        	JobDetail jobDetail = newJob(jobClass).withIdentity(jobName,jobGroupName)
                    .usingJobData("code", paramMap.get("code").toString())
                    .build(); // 任务名，任务组，任务执行类
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName,triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(time));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName
     * @param time
     */
    @SuppressWarnings("unchecked")
    public static void modifyJobTime(String jobName, String time) {
        try {
        	TriggerKey tk = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            //构造任务触发器
            Trigger trg = newTrigger()
                    .withIdentity(jobName, TRIGGER_GROUP_NAME)
                    .withSchedule(cronSchedule(time))
                    .build();
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.rescheduleJob(tk, trg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     * @param triggerName
     * @param triggerGroupName
     * @param time
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {
        try {
        	TriggerKey tk = TriggerKey.triggerKey(triggerName, triggerGroupName);
            //构造任务触发器
            Scheduler sched = gSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(tk);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(time));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                sched.rescheduleJob(tk, trigger);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName
     */
    public static void removeJob(String jobName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
        	TriggerKey tk = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            sched.pauseTrigger(tk);// 停止触发器
            sched.unscheduleJob(tk);// 移除触发器
            JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
            sched.deleteJob(jobKey);// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
        	TriggerKey tk = TriggerKey.triggerKey(jobName, triggerGroupName);
            sched.pauseTrigger(tk);// 停止触发器
            sched.unscheduleJob(tk);// 移除触发器
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            sched.deleteJob(jobKey);// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    public static void startJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public static void shutdownJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
