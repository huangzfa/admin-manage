package com.duobei.console.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/******
 * 扩展Shiro默认的用户认证的Bean - UsernamePasswordToken
 * 
 * @author ATH
 *
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	public UsernamePasswordCaptchaToken() {
		super();
	}

	public UsernamePasswordCaptchaToken(final String username,
			final char[] password, final boolean rememberMe, final String host) {
		super(username, password, rememberMe, host);
	}

}
