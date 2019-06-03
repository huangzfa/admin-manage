package com.duobei.utils;

import com.alibaba.fastjson.JSON;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component(value = "messageUtil")
public class MessageUtil {



    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    /**
     * 发送短信
     * @param
     */
    public void sendSms(String content){
        executorService.execute(()->{
          String result =  OkHttpUtil.okHttpPostJson(GlobalConfig.getMessageUrl()+"/sms/send", content);
          resultValid(result);
        });
    }

    private void resultValid(String result) {
        if (result == null){
            log.info("短信发送失败：网络异常");
            throw new RuntimeException("网络异常");
        }
        Map<String,Object> map = (Map<String, Object>) JSON.parse(result);
        if (map.get("code") ==null && !map.get("code").equals(1000)){
            log.info("短信发送失败："+map.get("message").toString());
            throw new RuntimeException(map.get("message").toString());
        }
    }

}
