package com.duobei.console.shiro;

import org.apache.shiro.authc.AuthenticationException;

/******
 * Shiro登录认证，用户已冻结异常
 * @author ATH
 *
 */
public class UserFreezeException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserFreezeException() {
        super();
    }

    public UserFreezeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserFreezeException(String message) {
        super(message);
    }

    public UserFreezeException(Throwable cause) {
        super(cause);
    }
}
