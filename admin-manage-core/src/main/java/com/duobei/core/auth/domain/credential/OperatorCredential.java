package com.duobei.core.auth.domain.credential;

import java.io.Serializable;
import java.util.Date;


/**
 * 管理系统凭证
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c)2015  
 * Company:		
 * Author:		洪捃能
 * Version:		1.0  
 * Created at:	2015年9月8日 下午2:17:38
 * </pre>
 */
public class OperatorCredential implements Serializable{
	private static final long serialVersionUID = -536849320571695483L;

	public static final String Credential_Key = "SESSION_credential_key";
	
	private Integer opId;
	private String loginName;
	private String realName;
	private String stateZd;
	
	private Integer organId;//所属组织
	private boolean isSuperAdmin=false;//是否是超级管理员-运维平台的
	
	private String ip;//登录ip
	private Date loginTime;//登录时间
	private Serializable sessionId;//会话id
	private Date sessionCreateTime;//会话创建时间
	private long sessionTimeout;//会话有效时间
	
	
	public long getSessionTimeout() {
		return sessionTimeout;
	}
	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	private String lastLoginIp;//上次登录ip
	private Date lastLoginTime;//上次登录时间
	
	
	public Date getSessionCreateTime() {
		return sessionCreateTime;
	}
	public void setSessionCreateTime(Date sessionCreateTime) {
		this.sessionCreateTime = sessionCreateTime;
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
	public Serializable getSessionId() {
		return sessionId;
	}
	public void setSessionId(Serializable sessionId) {
		this.sessionId = sessionId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}
	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	public Integer getOpId() {
		return opId;
	}
	public void setOpId(Integer opId) {
		this.opId = opId;
	}
	public String getStateZd() {
		return stateZd;
	}
	public void setStateZd(String stateZd) {
		this.stateZd = stateZd;
	}
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}
	@Override
	public String toString() {
		return "OperatorCredential [opId=" + opId + ", loginName=" + loginName
				+ ", realName=" + realName + ", stateZd=" + stateZd
				+ ", organId=" + organId + ", isSuperAdmin=" + isSuperAdmin
				+ ", ip=" + ip + ", loginTime=" + loginTime + ", sessionId="
				+ sessionId + ", sessionCreateTime=" + sessionCreateTime
				+ ", sessionTimeout=" + sessionTimeout + ", lastLoginIp="
				+ lastLoginIp + ", lastLoginTime=" + lastLoginTime + "]";
	}

}
