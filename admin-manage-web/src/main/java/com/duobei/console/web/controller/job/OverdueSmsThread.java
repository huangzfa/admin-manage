package com.duobei.console.web.controller.job;


import com.alibaba.fastjson.JSON;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.common.util.BeanUtil;
import com.duobei.config.GlobalConfig;
import com.duobei.core.message.sms.domain.SmsRecord;
import com.duobei.core.message.sms.domain.SmsTemplet;
import com.duobei.core.message.sms.service.SmsRecordService;
import com.google.common.base.Joiner;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**逾期线程发送类
 * Created by 15935 on 2018/9/3.
 */
@Data
@Slf4j
public class OverdueSmsThread implements Callable<Map<String,Object>> {

    static ThreadLocal<SmsRecord> localRecord =new ThreadLocal<SmsRecord>();

    @Override
    public Map<String,Object> call() throws Exception {

        //短信发送
        HashMap<String, Object> map = new HashMap<>();
        map.put("appKey", appKey);
        map.put("businessCode", businessCode);
        map.put("mobile", phone);
        map.put("templetCode", templetCode);
        HashMap<String, String> contentMap = new HashMap<>();
        contentMap.put("value", value+"");
        map.put("contentMap", contentMap);
        SmsRecordService smsRecordService = (SmsRecordService) BeanUtil.getBean("smsRecordService");
        try {

            SmsRecord record = new SmsRecord();
            localRecord.set(record);
            record.setBusinessCode(businessCode);
            record.setAppKey(appKey);
            record.setSendContent(content.replace("${value}",value+""));
            record.setSendMobile(phone);
            record.setTempletCode(templetCode);
            smsRecordService.save(record);
            record = null;
        }catch (Exception e){
            log.error("批量入smsRecord失败:"+e.getMessage());
        }
        if( GlobalConfig.isProdEnvironment()){
            String result = OkHttpUtil.okHttpPostJson(GlobalConfig.getMessageUrl()+"/sms/send", JSON.toJSONString(map));
            log.info("推送结果："+result);
        }
        map = null;
        return null;
    }

    private String phone;

    private String appKey;

    private String businessCode;

    private Object value;

    private String templetCode;

    private String content;

}
