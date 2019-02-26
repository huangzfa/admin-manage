package com.duobei.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.common.vo.JpushCustomVo;
import com.duobei.common.vo.JpushVo;
import com.duobei.common.vo.SmsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

public class MessageUtil {

    private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);

    private static String url;

    private static String systemCode;


    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        MessageUtil.url = url;
    }

    public static String getSystemCode() {
        return systemCode;
    }

    public static void setSystemCode(String systemCode) {
        MessageUtil.systemCode = systemCode;
    }


    /**
     * 短信接口
     */
    private static String SMS_INTERFACE;
    /**
     * 通过别名向用户推送消息接口
     */
    private static String JPUSH_MESSAGE_ALIAS_INTERFACE;
    /**
     * 通过注册ID推送消息接口
     */
    private static String JPUSH_MESSAGE_REGISTID_INTERFACE;
    /**
     * 通过别名向用户推送通知接口
     */
    private static String JPUSH_NOTIFY_ALIAS_INTERFACE ;

    /**
     * 通过注册ID推送通知接口
     */
    private static   String JPUSH_NOTIFY_REGISTID_INTERFACE;

    /**
     * 自定义push接口
     */
    private static String JPUSH_CUSTOM_INTERFACE ;
    public void initUtil(){
        SMS_INTERFACE = getUrl()+"/message/sms/sendSms";
        JPUSH_MESSAGE_ALIAS_INTERFACE = getUrl()+"/message/jpush/pushMessageByAlias";
        JPUSH_MESSAGE_REGISTID_INTERFACE =  getUrl()+"/message/jpush/pushMessageByRegistIds";
        JPUSH_NOTIFY_ALIAS_INTERFACE =  getUrl()+"/message/jpush/pushNotifyByAlias";
        JPUSH_NOTIFY_REGISTID_INTERFACE =  getUrl()+"/message/jpush/pushNotifyRegistIds";
        JPUSH_CUSTOM_INTERFACE =  getUrl()+"/message/jpush/pushCustom";
   }

    //核心线程数，最大线程数为10。超时时间为5秒,在任务完成后限制超过5秒就会被回收
    private ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            10, 5,TimeUnit.SECONDS,new SynchronousQueue<>());
    /**
     * 发送短信
     * @param smsVo
     */
    public void sendSms(SmsVo smsVo){
        executorService.execute(()->{
          String result =  OkHttpUtil.okHttpPostJson(SMS_INTERFACE,JSON.toJSONString(smsVo));
          resultValid(result);
        });
    }
    public static void main(String args[]){
        SmsVo smsVo = new SmsVo();
        smsVo.setMobile("17607184395");
        smsVo.setSmsTemptCode("ADMIN_LOGIN_VERIFY");
        JSONObject object= new JSONObject();
        object.put("value","8888");
        smsVo.setParamsJson(JSON.toJSONString(object));
        smsVo.setVerifyCode("8888");
        smsVo.setSystemCode("duobei");
        System.out.print(JSON.toJSONString(smsVo));
        String result =  OkHttpUtil.okHttpPostJson("http://tmservice.huashuangtec.com/message/sms/sendSms",JSON.toJSONString(smsVo));
        System.out.println(result);
    }
    private void resultValid(String result) {
        if (result == null){
            log.info("短信发送失败：网络异常");
            throw new RuntimeException("网络异常");
        }
        Map<String,Object> map = (Map<String, Object>) JSON.parse(result);
        if (map.get("code") ==null && !map.get("code").equals(1000)){
            log.info("短信发送失败："+map.get("msg").toString());
            throw new RuntimeException(map.get("msg").toString());
        }
    }

    /**
     * 通过别名向用户推送通知
     * @param jpushVo
     */
    public void pushNotifyByAlias(JpushVo jpushVo){
        push(JPUSH_NOTIFY_ALIAS_INTERFACE,jpushVo);
    }

    /**
     * 通过用户iD向用户推送通知
     * @param jpushVo
     */
    public void pushNotifyByRegisterIds(JpushVo jpushVo){
        push(JPUSH_NOTIFY_REGISTID_INTERFACE,jpushVo);
    }
    /**
     * 通过别名向用户推送通知
     * @param jpushVo
     */
    public void pushMessageByAlias(JpushVo jpushVo){
        push(JPUSH_MESSAGE_ALIAS_INTERFACE,jpushVo);
    }
    /**
     * 通过别名向用户推送通知
     * @param jpushVo
     */
    public void pushMessageByRegisterIds(JpushVo jpushVo){
        push(JPUSH_MESSAGE_REGISTID_INTERFACE,jpushVo);
    }

    /**
     * 自定义推送
     */
    public void pushCustom(JpushCustomVo jpushCustomVo){
        String result = null;
        jpushCustomVo.setSystemCode(systemCode);
        result = OkHttpUtil.okHttpPostJson(JPUSH_CUSTOM_INTERFACE,JSON.toJSONString(jpushCustomVo));
        resultValid(result);
    }

    /**
     * 推送
     */
    private void push(String url , JpushVo jpushVo){
        executorService.execute(()->{
            jpushVo.setSystemCode(systemCode);
            String result = OkHttpUtil.okHttpPostJson(url,JSON.toJSONString(jpushVo));
            resultValid(result);
        });
    }


}
