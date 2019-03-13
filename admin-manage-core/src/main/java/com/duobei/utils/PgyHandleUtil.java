package com.duobei.utils;

import com.pgy.data.handler.PgyDataHandler;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public class PgyHandleUtil {
    public static void main(String[] args) {
        System.out.println(PgyDataHandler.mobilePhone("13023676003"));
        System.out.println(PgyDataHandler.chineseName("李天雄"));
        System.out.println(PgyDataHandler.md5("李天雄43018119970320905X"));
        System.out.println(PgyDataHandler.idcard("43018119970320905X"));
    }
}
