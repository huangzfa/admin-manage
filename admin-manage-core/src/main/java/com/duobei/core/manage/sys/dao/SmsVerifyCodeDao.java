package com.duobei.core.manage.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.duobei.core.manage.sys.domain.SmsVerifyCode;

public interface SmsVerifyCodeDao {

	SmsVerifyCode queryLastSmsVerifyCode(@Param("mobile")String mobile, @Param("smsBizType")int smsBizType);
    
}