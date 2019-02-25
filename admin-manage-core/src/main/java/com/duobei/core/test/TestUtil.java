package com.duobei.core.test;

import com.pgy.data.handler.PgyDataHandler;

public class TestUtil {
    public static void main(String[] args) {
        String chineseName = "八神庵";
        String idcard = "412723199608124654";
        String fixedPhone = "44444444";
        String mobilePhone = "17621360219";
        String bankCard = "6228480329919406875";
        System.out.println("姓名：" + PgyDataHandler.chineseName(chineseName));
        System.out.println("身份证号：" + PgyDataHandler.idcard(idcard));
        System.out.println("手机号：" + PgyDataHandler.mobilePhone(mobilePhone));
        System.out.println("银行卡号：" + PgyDataHandler.bankCard(bankCard));
        System.out.println("固定电话：" +PgyDataHandler. fixedPhone(fixedPhone));
    }
}
