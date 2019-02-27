package com.duobei.core.auth.domain.credential;

import com.duobei.core.app.domain.App;
import com.duobei.core.product.domain.Product;
import com.sun.tools.javac.util.List;
import lombok.Data;

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
@Data
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
	private String lastLoginIp;//上次登录ip
	private Date lastLoginTime;//上次登录时间
	private List<Product> productList;//角色产品权限
	private List<App> appList;//角色应用权限

}
