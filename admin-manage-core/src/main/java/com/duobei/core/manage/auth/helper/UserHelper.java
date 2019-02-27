package com.duobei.core.manage.auth.helper;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;


/**
 * 用户帮助类
 * @author Hong
 *
 */
public class UserHelper {

	/**
	 * 创建用户通行证
	 * @param loginSubject
	 * @return
	 */
	public static OperatorCredential createCredential(Operator operator,String superAdmin){
		OperatorCredential credential=new OperatorCredential();
		credential.setOpId(operator.getOpId());
		credential.setLoginName(operator.getLoginName());
		credential.setRealName(operator.getRealName());
		credential.setStateZd(operator.getOperatorState());
		credential.setOrganId(operator.getOrganId());//所在组织
		
		if (credential.getLoginName().equals(superAdmin)) {
			credential.setSuperAdmin(true);//是否是超级管理员
		}else{
			credential.setSuperAdmin(false);//是否是超级管理员
		}
		credential.setLoginTime(new Date());
		credential.setLastLoginIp(operator.getLoginIp());
		credential.setLastLoginTime(operator.getLoginTime());
		return credential;
	}
	/**
	 * 获取运维用户凭证
	 * @return
	 */
	public static OperatorCredential getCredential() {
		Subject subject = SecurityUtils.getSubject();
		OperatorCredential myCredential=(OperatorCredential) subject.getPrincipal();
		return myCredential;
	}
	/**
	 * 是否是超级管理员
	 * @return
	 */
	public static boolean isSuperAdmin() {
		return getCredential().isSuperAdmin();
	}
	/**
	 * 强制踢出
	 */
	public static void forceLogout(String sessionId,String outKey)throws Exception {
		if(StringUtils.isNotBlank(sessionId)){
			DefaultWebSecurityManager securityManager=(DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
			DefaultWebSessionManager sessionManager=(DefaultWebSessionManager) securityManager.getSessionManager();
			try {
				Session session=sessionManager.getSession(new DefaultSessionKey(sessionId));
				if (session!=null) {
					session.setAttribute(outKey, "1");
				}
			} catch (Exception e) {
			}
		}
	}
	
}
