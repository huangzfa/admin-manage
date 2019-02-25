package com.duobei.common.util;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

public class OkHttp3Util {

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
            e.printStackTrace();
        }
        return result;
    }
    public static String okHttpGet(String url,String headJson) {
        String result = null;
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();;
        Request request = new Request.Builder().addHeader("heads",headJson).url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param url 地址
     * @param headJson 请求头
     * @param connectTimeout 连接时间
     * @param readTimeout 读取时间
     * @return
     */
    public static String okHttpGet(String url,String headJson,Long connectTimeout,Long readTimeout) {
        String result = null;
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS).build();;
        Request request = new Request.Builder().addHeader("heads",headJson).url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String okHttpPostJson(String url, String json){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String okHttpPostJson(String url, String json,String headJson){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .header("heads",headJson)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String okHttpPostJson(String url, String json,String headJson,Long connectTimeout,Long readTimeout){
        String result = null;
        OkHttpClient client =new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .header("heads",headJson)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
