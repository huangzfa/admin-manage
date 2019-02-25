package com.duobei.common.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String SMS_CODE_TEST="8888";//默认短信验证码

    public static final String LOGIN_PASSWORD="123456";//默认登录密码
    /**线下还款锁释放时间**/
    public static final long REDIS_EXPIRE_REPAY_OFFLINE = 30 * 1000;
    /**可重复获取锁的次数**/
    public static final int REDIS_GET_REPEAT_REPAY_OFFLINE = 5;

    //最新手机号正则
    public static final String PHONE_REGEX = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
    //支付宝 账务明细 数据导入  账号转账
    public static final Map<Integer,String> BILLING_DETAIL_IMPORT= new HashMap<Integer,String>(){
        {
            put(0,"line");
            put(1,"realRepayDate");
            put(2,"tradNo");
            put(3,"alipayOrderNo");
            put(6,"repayAmount");
            put(12,"userAlipayNo");
            put(13,"userAlipayName");
            put(16,"remark");
        }
    };
    //支付宝 交易明细 数据导入 二维码付款
    public static final Map<Integer,String> TRAD_DETAIL_IMPORT= new HashMap<Integer,String>(){
        {
            put(0,"line");
            put(1,"realRepayDate");
            put(3,"alipayOrderNo");
            put(4,"tradNo");
            put(5,"userAlipayNo");
            put(6,"userAlipayName");
            put(7,"repayAmount");
        }
    };


    public static final int ZFB_REPAY_FAILED = -1;
    public static final int ZFB_REPAY_SUCCESS = 1;
    public static final int ZFB_REPAY_WAIT_FLAT = 0 ;

    public static final String PRODUCT_DEFAULT_CHANNEL = "0";


}