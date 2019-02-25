package com.duobei.console.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordCredentialsMatcher implements CredentialsMatcher {

	private final static Logger log = LoggerFactory.getLogger(PasswordCredentialsMatcher.class);

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String loginPwd = new String((char[]) token.getCredentials());
		String pwd = (String) info.getCredentials();
		System.out.println("===========>" + loginPwd + "===" + pwd);
		boolean result = false;
		try {
			result = loginPwd.equals(pwd);
		} catch (Exception e) {
			log.error("校验密码错误", e);
			result = false;
		}
		return result;
	}
}