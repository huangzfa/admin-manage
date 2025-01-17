package com.duobei.core.manage.auth.domain;

import java.io.Serializable;
import java.util.Date;

public class Operator implements Serializable {
    /**
     * 
     */
    private Integer opId;

    /**
     * 
     */
    private String loginName;

    /**
     * 
     */
    private String loginPwd;

    /**
     * 
     */
    private String realName;

    /**
     * 
     */
    private String operatorState;

    /**
     * 
     */
    private Integer organId;

    /**
     * 
     */
    private String sessionId;

    /**
     * 
     */
    private String loginIp;

    /**
     * 
     */
    private Date loginTime;

    /**
     * 
     */
    private String lastLoginIp;

    /**
     * 
     */
    private Date lastLoginTime;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 
     */
    private Integer addOperatorId;

    /**
     * 
     */
    private String addOperatorName;

    /**
     * 
     */
    private Integer modifyOperatorId;

    /**
     * 
     */
    private String modifyOperatorName;

    /**
     * 
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table aa_operator
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    private static final long serialVersionUID = -704349134575146210L;

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOperatorState() {
        return operatorState;
    }

    public void setOperatorState(String operatorState) {
        this.operatorState = operatorState;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getAddOperatorId() {
        return addOperatorId;
    }

    public void setAddOperatorId(Integer addOperatorId) {
        this.addOperatorId = addOperatorId;
    }

    public String getAddOperatorName() {
        return addOperatorName;
    }

    public void setAddOperatorName(String addOperatorName) {
        this.addOperatorName = addOperatorName;
    }

    public Integer getModifyOperatorId() {
        return modifyOperatorId;
    }

    public void setModifyOperatorId(Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }

    public String getModifyOperatorName() {
        return modifyOperatorName;
    }

    public void setModifyOperatorName(String modifyOperatorName) {
        this.modifyOperatorName = modifyOperatorName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", opId=").append(opId);
        sb.append(", loginName=").append(loginName);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", realName=").append(realName);
        sb.append(", operatorState=").append(operatorState);
        sb.append(", organId=").append(organId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", addOperatorName=").append(addOperatorName);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append(", modifyOperatorName=").append(modifyOperatorName);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}