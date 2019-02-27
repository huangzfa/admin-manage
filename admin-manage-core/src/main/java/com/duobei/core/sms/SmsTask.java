package com.duobei.core.sms;

import com.duobei.core.manage.sys.service.VerifyCodeService;

public class SmsTask implements Runnable {

	private int notifyBizType;
	private String mobile;
	private VerifyCodeService verifyCodeService;
	

	public SmsTask(int notifyBizType, String mobile,
			VerifyCodeService verifyCodeService) {
		this.notifyBizType = notifyBizType;
		this.mobile = mobile;
		this.verifyCodeService = verifyCodeService;
	}

	@Override
	public void run() {
//		try {
//			verifyCodeService.sendVerifyCode(mobile, notifyBizType);
//		} catch (TqException e) {
//		}
	}

}
