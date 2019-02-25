package com.duobei.common.vo;

/**
 * 短信服务实体类
 *
 * @version v1.0.0
 * @author: LiTianxiong
 * @date: 2018年12月18日 下午16:20:32
 */

public class SmsVo {
    /**
     * 模板编码（必须存在）
     */
    private String smsTemptCode;
    /**
     * 用户手机号（必须存在）
     */
    private String mobile;
    /**
     * 模板替换值JSON
     */
    private String paramsJson;
    /**
     * 验证码
     */
    private String verifyCode;

    private String systemCode;


    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getSmsTemptCode() {
        return smsTemptCode;
    }

    public void setSmsTemptCode(String smsTemptCode) {
        this.smsTemptCode = smsTemptCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getParamsJson() {
        return paramsJson;
    }

    public void setParamsJson(String paramsJson) {
        this.paramsJson = paramsJson;
    }
}
