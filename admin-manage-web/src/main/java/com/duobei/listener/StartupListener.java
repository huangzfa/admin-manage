package com.duobei.listener;

import com.duobei.common.util.QuartzManager;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.service.QuartzInfoService;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 在web 项目中（spring mvc），系统会存在两个容器，一个是root application context
 * 另一个就是我们自己的 projectName-servlet  context（作为root application context的子容器）。
 * 这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免上面提到的问题，我们可以只在root application context
 * 初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理，修改后代码
 * @date 2019/5/17
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger log = LoggerFactory.getLogger(StartupListener.class);

    @Autowired
    private QuartzInfoService quartzInfoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
		if(evt.getApplicationContext().getParent() == null){//防止调取两次
			log.info("初始化定时任务-开始");
			List<QuartzInfo> list = quartzInfoService.getStart();
			for(QuartzInfo info : list){
				//添加任务到定时器
				try {
					Object cl = Class.forName(info.getClassName()).newInstance();
					JobDataMap jobDataMap = new JobDataMap();
					jobDataMap.put("code",info.getCode());
					QuartzManager.addJob(info.getCode(),jobDataMap,info.getJobGroupName(),info.getTriggerName(),info.getTriggerGroupName(),cl.getClass(),info.getCycle());

				}catch (Exception e){
					log.error("初始化定时任务异常："+e.getMessage());
				}
			}
			log.info("初始化定时任务-结束");
		}
    }
}
