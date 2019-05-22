package com.duobei.core.operation.push.service.impl;


import com.alibaba.fastjson.JSON;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.config.GlobalConfig;
import com.duobei.core.operation.push.domain.PushRecord;
import com.google.common.base.Joiner;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**极光推送线程类
 * Created by huangzhongfa on 2019/9/3.
 */
@Data
@Slf4j
public class PushSysThread implements Callable<Map<String,Object>> {

    @Override
    public Map<String,Object> call() throws Exception {
        HashMap<String,Object> pushMap = new HashMap<>();
        pushMap.put("appKey",appKey);
        pushMap.put("content",record.getPushContent());
        pushMap.put("isNotice",true);//通知
        pushMap.put("isPushAll",false);//推送所有人
        pushMap.put("title",record.getPushTitle());//推送标题
        pushMap.put("plateforms","1,2");//推送平台
        pushMap.put("alias", Joiner.on(",").join(userList));
        log.info("========================="+ JSON.toJSONString(pushMap));
        if(GlobalConfig.isProdEnvironment()){
            String result = OkHttpUtil.okHttpPostJson(GlobalConfig.getMessageUrl()+"//pushMsg/jiguang", JSON.toJSONString(pushMap));
            log.info("推送结果："+result);
        }
        return  null;
    }

    private List<Object> userList;

    private PushRecord record;

    private String appKey;
}
