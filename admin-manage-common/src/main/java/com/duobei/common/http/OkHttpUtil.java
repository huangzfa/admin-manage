package com.duobei.common.http;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
    private static final Logger log = LoggerFactory.getLogger(OkHttpUtil.class);

    //请求超时
    private static final Long outTime = 60L;

    public static final MediaType FORM_CONTENT_TYPE_JSON
            = MediaType.parse("application/json;charset=UTF-8");

    /**
     * 发起get请求
     *
     * @param url
     * @return
     */
    public static String okHttpGet(String url) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            log.error("请求url：{},请求失败",url);
            e.printStackTrace();
        }
        log.info("请求url：{},请求成功",url);
        return result;
    }

    /**
     * 发起带请求头的get请求
     * @param url
     * @param headJson
     * @return
     */
    public static String okHttpGet(String url,String headJson) {
        String result = null;
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();;
        Request request = new Request.Builder().addHeader("heads",headJson).url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            log.error("请求url：{},请求头参数:{},请求失败",url,headJson);
            e.printStackTrace();
        }
        log.info("请求url：{},请求头参数:{},请求成功",url,headJson);
        return result;
    }

    /**
     * 发起post请求
     *
     * @param url
     * @return
     */
    public static String okHttpPostJson(String url, String json){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            log.error("请求url：{},请求参数:{},请求失败,异常",url,json);
        }
        log.info("请求url：{},请求参数:{},请求成功",url,json);
        return result;
    }

    /**
     * 发起带请求头的post请求
     *
     * @param url
     * @return
     */
    public static String okHttpPostJson(String url, String json,String headJson){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(FORM_CONTENT_TYPE_JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .header("heads",headJson)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            log.error("请求url：{},请求参数:{},请求头参数：{},请求失败",url,json,headJson);
            e.printStackTrace();
        }
        log.info("请求url：{},请求参数:{},请求头参数：{},请求成功",url,json,headJson);
        return result;
    }

    /**
     * 发起form表单提交请求
     *
     * @param url
     * @return
     */
    public static String okHttpPostForm(String url, HashMap<String,String> paramsMap){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();

        FormBody.Builder builder  = new FormBody.Builder();
        for (String key : paramsMap.keySet()) {
            //追加表单信息
            builder .add(key, paramsMap.get(key));
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            log.error("请求url：{},请求参数:{},请求失败,异常",url,paramsMap);
        }
        log.info("请求url：{},请求参数:{},请求成功",url,paramsMap);
        return result;
    }

    /**
     * 发起post请求
     *
     * @param url
     * @param outTime
     * @return
     */
    public static String okHttpPostJson(String url, String json,Long outTime){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(outTime, TimeUnit.SECONDS).readTimeout(outTime, TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type","application/json;charset=UTF-8")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            log.error("请求url：{},请求参数:{},请求失败,异常",url,json);
        }
        log.info("请求url：{},请求参数:{},请求成功",url,json);
        return result;
    }


    /**
     * 发起form表单提交请求
     *
     * @param url
     * @return
     */
    public static String okHttpPostForm(String url, Map<String,String> paramsMap, Long outTime){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(outTime, TimeUnit.SECONDS).readTimeout(outTime, TimeUnit.SECONDS).build();

        FormBody.Builder builder  = new FormBody.Builder();
        StringBuilder sb = new StringBuilder("?");
        for (String key : paramsMap.keySet()) {
            //追加表单信息
                builder.add(key, paramsMap.get(key));
        }

        //RequestBody body = RequestBody.create(FORM_CONTENT_TYPE_JSON,sb.toString());
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type","application/json;charset=UTF-8")
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            log.error("请求url：{},请求参数:{},请求失败,异常",url,paramsMap);
        }
        log.info("请求url：{},请求参数:{},请求成功",url,paramsMap);
        return result;
    }


}
