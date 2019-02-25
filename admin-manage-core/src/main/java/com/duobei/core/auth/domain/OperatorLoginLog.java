package com.duobei.core.auth.domain;

import java.io.Serializable;
import java.util.Date;

public class OperatorLoginLog implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Integer opId;

    /**
     * HTTPSESSIONID 或 终端SESSIONID
     */
    private String sessionId;

    /**
     * 1-登录，0-登出
     */
    private String loginType;

    /**
     * 
     */
    private String ip;

    /**
     * 原因
     */
    private String reason;

    /**
     * 
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    private static final long serialVersionUID = 3283848083005869085L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", opId=").append(opId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", loginType=").append(loginType);
        sb.append(", ip=").append(ip);
        sb.append(", reason=").append(reason);
        sb.append(", addTime=").append(addTime);
        sb.append("]");
        return sb.toString();
    }
}