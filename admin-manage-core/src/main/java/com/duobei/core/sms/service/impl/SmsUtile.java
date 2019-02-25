package com.duobei.core.sms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.duobei.common.http.HttpClientUtil;
import com.duobei.common.util.encrypt.DigestUtil;

public class SmsUtile {
	private final static Logger log = LoggerFactory.getLogger(SmsUtile.class);

	public final static String smsUrl = "http://www.dh3t.com/json/sms/Submit";

	// private static final String OPERATOR_UDPATE_PASSWORD = "验证码:$code
	// ,您的账号正在修改业务后台登录密码，请勿将验证码泄露给其他人。";
	private static final String OPERATOR_USER_LOGIN = "验证码:$code ,您的账号正在登录业务后台，请勿将验证码泄露给其他人。";

	public static boolean sendSms(String mobile, String content) throws Exception {
		String account = "";
		String password = "";
		String SIGN = "";

		account = "dh16001";
		password = "1Ptpe6L5";
		SIGN = "【美期分期】";

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("account", account);
		paramsMap.put("password", DigestUtil.MD5(password).toLowerCase());
		paramsMap.put("phones", mobile);
		paramsMap.put("content", content);
		paramsMap.put("sign", SIGN);

		String reqResult = HttpClientUtil.postData(smsUrl, JSON.toJSONString(paramsMap));
		System.out.println("发送短信内容：" + JSON.toJSONString(paramsMap) + ";发送短信结果：" + reqResult);
		log.info("发送短信内容：" + JSON.toJSONString(paramsMap) + ";发送短信结果：" + reqResult);

		JSONObject json = JSON.parseObject(reqResult);
		if (json.getInteger("result") == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean sendLoginCode(String mobile, String code) throws Exception {
		return sendSms(mobile, OPERATOR_USER_LOGIN.replace("$code", code));
	}

	public static void main(String[] args) throws Exception {
		System.out.println(SmsUtile.sendLoginCode("18668299908", "8888"));
	}
}
