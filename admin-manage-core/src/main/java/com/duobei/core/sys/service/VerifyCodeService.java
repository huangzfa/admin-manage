package com.duobei.core.sys.service;

import com.duobei.core.sys.domain.SmsVerifyCode;
import com.duobei.common.exception.TqException;

public interface VerifyCodeService {

	/**
	 * 校验验证码
	 * @param mobile 手机号
	 * @param verifyCode 验证码
	 * @param smsBizType 短信业务类型
	 * @return
	 * @throws TqException 
	 */
	void checkVerifyCode(String mobile,String verifyCode,int smsBizType) throws TqException;


	SmsVerifyCode queryLastSmsVerifyCode(String mobile, int smsBizType);

	/**
	 * 重写发送短信验证码
	 * @param mobile
	 * @param smsBizType
	 * @throws RuntimeException
	 */
	void sendSms(String mobile,int smsBizType) throws RuntimeException;
	
}
