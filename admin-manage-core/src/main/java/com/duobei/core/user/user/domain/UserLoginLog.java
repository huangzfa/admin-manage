package com.duobei.core.user.user.domain;

import java.io.Serializable;
import java.util.Date;

public class UserLoginLog implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名-手机号密文无解，用于匹配
     */
    private String userNameMd5;

    /**
     * 版本号（整数）
     */
    private String appVersion;

    /**
     * 操作系统_版本,如：android_6.1.0
     */
    private String osType;

    /**
     * 手机型号：如iPhone6s,HUAWEI GRA-CL00
     */
    private String phoneType;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 
     */
    private Date addTime;

    /**
     * 产品Id
     */
    private Integer productId;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pgy_user_login_log
     *
     * @mbg.generated Wed Apr 17 11:27:16 CST 2019
     */
    private static final long serialVersionUID = 2887896443000165416L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNameMd5() {
        return userNameMd5;
    }

    public void setUserNameMd5(String userNameMd5) {
        this.userNameMd5 = userNameMd5;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_login_log
     *
     * @mbg.generated Wed Apr 17 11:27:16 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userNameMd5=").append(userNameMd5);
        sb.append(", appVersion=").append(appVersion);
        sb.append(", osType=").append(osType);
        sb.append(", phoneType=").append(phoneType);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", addTime=").append(addTime);
        sb.append(", productId=").append(productId);
        sb.append(", appId=").append(appId);
        sb.append("]");
        return sb.toString();
    }
}