package com.duobei.console.web.controller.job;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.BorrowStateEnum;
import com.duobei.common.enums.SmsTempletEnum;
import com.duobei.common.enums.SmsUserfulCodeEnums;
import com.duobei.common.util.BeanUtil;
import com.duobei.core.message.sms.domain.SmsTemplet;
import com.duobei.core.message.sms.service.SmsTempletService;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.service.QuartzInfoService;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.user.user.domain.User;
import com.duobei.core.user.user.service.UserService;
import com.pgy.data.handler.PgyDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**即将逾期提醒
 * @author huangzhongfa
 * @description
 * @date 2019/5/17
 */
@Component
@Lazy(value = false)
@Slf4j
public class QuartzBeginOverdueRemind implements Job {

    private ExecutorService executorService = new ThreadPoolExecutor(0,Runtime.getRuntime().availableProcessors() + 1,60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    @Override
    public void execute(JobExecutionContext context){
        JobDataMap paramMap = context.getJobDetail().getJobDataMap();
        if( paramMap!=null && paramMap.size()> BizConstant.INT_ZERO){
            String code = paramMap.getString("code");
            QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
            //查询定时任务
            QuartzInfo quartzInfo = quartzInfoService.getByCode(code);
            if( quartzInfo!=null && quartzInfo.getState().equals(BizConstant.INT_ONE)){
                BorrowCashService borrowCashService = (BorrowCashService) BeanUtil.getBean("borrowCashService");
                UserService userService = (UserService) BeanUtil.getBean("userService");
                AppService appService = (AppService) BeanUtil.getBean("appService");
                SmsTempletService templetService = (SmsTempletService) BeanUtil.getBean("smsTempletService");
                HashMap<String,Object> map = new HashMap<>();
                map.put("borrowState", BorrowStateEnum.PENDING_REPAYMENT.getCode());
                for(int i = -3; i < 0 ;i++){//i代表天数
                    //即将逾期前一天，0<还款时间-当前时间 <=1440,(单位 分钟)
                    //即将逾期前两天，1440<还款时间-当前时间 <=2880,(单位 分钟)
                    //即将逾期前三天，2880<还款时间-当前时间 <=4320,(单位 分钟)
                    map.put("code", SmsTempletEnum.findCodeByDay(i));//获取天数对应的code
                    List<BorrowCash> list = borrowCashService.getBeginOverdue(map);
                    SmsTemplet templet = templetService.getByCode(map.get("code").toString());
                    if( list.size() > BizConstant.INT_ZERO && templet!=null && templet.getState()){
                        //推送人员id
                        List<Long> userIdList = list.stream().map(BorrowCash::getUserId).collect(Collectors.toList());
                        //推送的对象
                        List<User> userList = userService.getByListId(userIdList);
                        //应用列表
                        List<App> appList = appService.getAll();
                        for(User user : userList){
                            String phone = PgyDataHandler.decrypt(user.getUserNameEncrypt());
                            Long value = getIndex(list,user.getId());
                            if( value == 0L){
                                continue;
                            }
                            OverdueSmsThread thread = null;
                            for(App app:appList){
                                if(user.getAppId().equals(app.getId())){
                                    thread = new OverdueSmsThread();
                                    thread.setBusinessCode(SmsUserfulCodeEnums.COLLECTION.getCode());
                                    thread.setAppKey(app.getAppKey());
                                    thread.setPhone(phone);
                                    thread.setContent(templet.getContent());
                                    thread.setTempletCode(map.get("code").toString());
                                    thread.setValue(value/100);
                                    executorService.submit(thread);
                                    break;
                                }
                            }
                        }
                    }
                }
                QuartzInfo entity = new QuartzInfo()
                        .setId(quartzInfo.getId())
                        .setCode(quartzInfo.getCode())
                        .setSucceed(quartzInfo.getSucceed()+BizConstant.INT_ONE);
                try {
                    quartzInfoService.update(entity);
                }catch (Exception e){
                    log.error(entity.getName()+"定时任务更新失败："+e.getMessage());
                }
            }
        }
    }

    /**
     * 获取借款金额
     * @param list
     * @param userId
     * @return
     */
    public Long getIndex(List<BorrowCash> list,Long userId){
        Long amount = 0L;
        for(BorrowCash cash : list){
            if(cash.getUserId().equals(userId)){
                amount = cash.getAmount();
                break;
            }
        }
        return amount;
    }
}
