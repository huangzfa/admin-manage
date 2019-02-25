package com.duobei.core.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.duobei.core.sys.domain.SmsVerifyCode;

public interface SmsVerifyCodeDao {

	SmsVerifyCode queryLastSmsVerifyCode(@Param("mobile")String mobile, @Param("smsBizType")int smsBizType);
    
}