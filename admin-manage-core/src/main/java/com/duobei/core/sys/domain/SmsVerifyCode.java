package com.duobei.core.sys.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SmsVerifyCode implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 短信类型
     */
    private Integer smsBizType;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 失效时间
     */
    private Date invalidTime;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Date modifyTime;


}