package com.duobei.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.common.exception.TqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**风控调用
 * Created by huangzhongfa on 2018/12/21.
 */
@Component(value = "riskUtil")
public class RiskUtil {

    private static final Logger logger = LoggerFactory.getLogger(RiskUtil.class);

    private static String url;

    private static String appId;

    private static String merId;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        RiskUtil.url = url;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        RiskUtil.appId = appId;
    }

    public static String getMerId() {
        return merId;
    }

    public static void setMerId(String merId) {
        RiskUtil.merId = merId;
    }

    /**
     * 判断场景id是否存在
     * @param sceneCode 场景id
     * @return
     * @throws Exception
     */
    public String SceneCodeHad(String sceneCode) throws TqException{
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("appId", appId);
        paramsMap.put("merId", merId);
        paramsMap.put("sceneCode", sceneCode);
        String result = OkHttpUtil.okHttpPostForm(url+"/tianqing/api/exist/scene.htm", paramsMap);
        logger.info("场景id校验 result:{}",result);
        if (result == null) {
            return "风控调取失败";
        }
        try {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
            if (map.get("code")==null || !map.get("code").equals("0000")) {
                return map.get("msg")==null?result:map.get("msg").toString();
            }
            return "success";
        }catch (Exception e){
            return result;
        }
    }

    /**
     * 推送逾期信息到风控
     * @param list
     * @param merOrderNo
     * @return
     * @throws Exception
     */
    public void pushOverdueForRisk(List<Map<String,Object>> list, String merOrderNo) {

        HashMap<String,String> paramsMap = new HashMap<>();
        try{

            String json =  JSONArray.toJSONString(list);
            paramsMap.put("appId", appId);
            paramsMap.put("merId", merId);
            paramsMap.put("merOrderNo", merOrderNo);
            paramsMap.put("details",json);
            logger.info("推送逾期信息到风控!paramsMap{}",paramsMap);
            OkHttpUtil.okHttpPostForm(url + "/tianqing/api/borrow/overdue.htm", paramsMap);


        }catch(Exception e){
            logger.info("推送逾期信息到风控发生异常!paramsMap{}",merOrderNo,paramsMap);
        }

    }
    /**
     * 推送还款信息到风控
     * @param map
     * @param merOrderNo
     * @return
     * @throws Exception
     */
    public void pushRepaymentForRisk(Long userId,String borrowNo, String merOrderNo,Map<String,Object> map) {

        HashMap<String,String> paramsMap = new HashMap<>();
        try{

            String json = JSONObject.toJSONString(map);
            paramsMap.put("appId", appId);
            paramsMap.put("merId", merId);
            paramsMap.put("merUserId", userId.toString());
            paramsMap.put("merOrderNo", merOrderNo);
            paramsMap.put("borrowNo", merOrderNo);
            paramsMap.put("details",json);
            logger.info("推送还款信息到风控!paramsMap{}",paramsMap);
            OkHttpUtil.okHttpPostForm(url + "/tianqing/api/borrow/repayment.htm", paramsMap);


        }catch(Exception e){
            logger.info("推送还款信息到风控发生异常!paramsMap{}",merOrderNo,paramsMap);
        }

    }

}
