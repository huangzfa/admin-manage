package com.duobei.console.shiro;

import org.apache.shiro.authc.AuthenticationException;

/******
 * Shiro登录认证，验证码错误异常
 * 
 * @author ATH
 *
 */
public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public CaptchaException() {
		super();
	}

	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaptchaException(String message) {
		super(message);
	}

	public CaptchaException(Throwable cause) {
		super(cause);
	}
}