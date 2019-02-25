package com.duobei.core.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.TemplateCodeEnum;
import com.duobei.common.util.Constants;
import com.duobei.common.vo.SmsVo;
import com.duobei.core.handler.VerifyCodeFailHandler;
import com.duobei.core.message.dao.SmsTempletDao;
import com.duobei.core.message.domain.SmsTemplet;
import com.duobei.core.utils.MessageUtil;
import com.pgy.data.handler.service.PgyDataHandlerService;
import com.pgy.data.handler.service.impl.PgyDataHandlerServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duobei.common.util.RegExpValidatorUtils;
import com.duobei.config.GlobalConfig;
import com.duobei.core.sms.service.impl.SmsUtile;
import com.duobei.core.sys.dao.SmsVerifyCodeDao;
import com.duobei.core.sys.dao.mapper.SmsVerifyCodeMapper;
import com.duobei.core.sys.domain.SmsVerifyCode;
import com.duobei.core.sys.service.VerifyCodeService;
import com.duobei.exception.TqException;

@Service("VerifyCodeService")
public class VerifyCodeDBServiceImpl implements VerifyCodeService {
	private final static Logger log = LoggerFactory.getLogger(VerifyCodeDBServiceImpl.class);
	private final int verifyCodeLength=4;
	@Autowired
	private SmsVerifyCodeMapper smsVerifyCodeMapper;
	@Autowired
	private SmsVerifyCodeDao smsVerifyCodeDao;
	@Autowired
	private MessageUtil messageUtil;
	@Autowired
	private VerifyCodeFailHandler verifyCodeFailHandler;
	@Autowired
	private SmsTempletDao smsTempletDao;
	

	@Override
	public void checkVerifyCode(String mobile, String verifyCode, int smsBizType) throws TqException {
		//校验手机号
		if(!RegExpValidatorUtils.isMobile(mobile)){
			throw new TqException("无效的手机号");
		}
		//校验验证码
		if(!RegExpValidatorUtils.isNumberExt(verifyCode)||verifyCode.length()!=verifyCodeLength){
			verifyCodeFailHandler.updateVerifyCodeFail(mobile,smsBizType);
			throw new TqException("无效的验证码");
		}
		//判断验证码是否相等,判断验证码是否失效
		SmsVerifyCode smsVerifyCode = queryLastSmsVerifyCode(mobile,smsBizType);
		if (smsVerifyCode == null || !verifyCode.equals(smsVerifyCode.getVerifyCode())
				|| System.currentTimeMillis() > smsVerifyCode.getInvalidTime().getTime()) {
			verifyCodeFailHandler.updateVerifyCodeFail(mobile, smsBizType);
			throw new TqException("无效的验证码");
		}
	}
	
	@Override
	public SmsVerifyCode queryLastSmsVerifyCode(String mobile, int smsBizType){
		return smsVerifyCodeDao.queryLastSmsVerifyCode(mobile,smsBizType);
	}

	/**
	 * 重写发送短信验证码
	 * @param mobile
	 * @param smsBizType
	 * @throws RuntimeException
	 */
	@Override
	public void  sendSms(String mobile,int smsBizType) throws RuntimeException{

		String verifyCode=RandomStringUtils.random(verifyCodeLength, false, true);
		SmsVerifyCode smsVerifyCode = queryLastSmsVerifyCode(mobile,smsBizType);
		if(smsVerifyCode!=null&&smsVerifyCode.getInvalidTime().getTime()>System.currentTimeMillis()){
			verifyCode=smsVerifyCode.getVerifyCode();
		}
		if (!GlobalConfig.isProdEnvironment()) {
			verifyCode= Constants.SMS_CODE_TEST;
		}
		try {
			SmsVerifyCode record=new SmsVerifyCode();
			record.setMobile(mobile);
			record.setSmsBizType(smsBizType);
			record.setVerifyCode(verifyCode);
			record.setInvalidTime(new Date(System.currentTimeMillis()+30*60*1000));
			record.setAddTime(new Date());
			if(1!=smsVerifyCodeMapper.insertSelective(record)){
				throw new RuntimeException("短信发送失败");
			}
			if (GlobalConfig.isProdEnvironment()) {
				// TODO 发送短信验证码
				PgyDataHandlerService pgyDataHandlerService = new PgyDataHandlerServiceImpl();
				Map<String, Object> map = new HashMap<>();
				map.put("value", verifyCode);
				SmsVo smsVo = new SmsVo();
				smsVo.setVerifyCode(verifyCode);
				smsVo.setMobile(mobile);
				smsVo.setParamsJson(JSONObject.toJSONString(map));
				SmsTemplet smsTemplet = smsTempletDao.getTempletByTempletCodeAndPlatform(TemplateCodeEnum.findByType(smsBizType),BizConstant.PLATFORM);
				if( smsTemplet == null ){
					throw new RuntimeException("短信模板不存在");
				}
				smsVo.setSmsTemptCode(smsTemplet.getTempletCode());
				smsVo.setSystemCode(BizConstant.PLATFORM);
				messageUtil.sendSms(smsVo);
			}

		} catch (Exception e) {
			log.info("["+mobile+","+smsBizType+"]发送短信验证码入库异常",e);
			throw new RuntimeException("短信发送失败");
		}
	}
}
