package com.duobei.core.operation.push.service.impl;


import com.alibaba.fastjson.JSON;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.common.util.BeanUtil;
import com.duobei.config.GlobalConfig;
import com.duobei.core.message.sms.service.SmsRecordService;
import com.google.common.base.Joiner;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**消息短信线程发送类
 * Created by 15935 on 2018/9/3.
 */
@Data
@Slf4j
public class PushSmsThread implements Callable<Map<String,Object>> {

    static ThreadLocal<HashMap<String,Object>> message_local =new ThreadLocal<HashMap<String,Object>>();

    @Override
    public Map<String,Object> call() throws Exception {

        //营销短信发送
        HashMap<String, Object> map = new HashMap<>();
        message_local.set(map);
        SmsRecordService smsRecordService = (SmsRecordService) BeanUtil.getBean("smsRecordService");
        try {
            message_local.get().put("appKey", appKey);
            message_local.get().put("phoneList",phoneList);
            message_local.get().put("businessCode", businessCode);
            message_local.get().put("content", content);
            smsRecordService.batchSave(message_local.get());

        }catch (Exception e){
            log.error("批量入smsRecord失败:"+e.getMessage());
        }
        if( GlobalConfig.isProdEnvironment()){
            message_local.get().put("appKey", appKey);
            message_local.get().put("mobile", Joiner.on(",").join(phoneList));
            message_local.get().put("content",content);
            message_local.get().put("businessCode",businessCode);
            String result = OkHttpUtil.okHttpPostJson(GlobalConfig.getMessageUrl(), JSON.toJSONString(map));
            log.info("推送结果："+result);
        }

        return null;
    }

    private List<Object> phoneList;

    private String content;

    private String appKey;

    private String businessCode;



}
