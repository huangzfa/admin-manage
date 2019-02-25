package com.duobei.console.shiro;

import com.alibaba.fastjson.JSON;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.config.GlobalConfig;
import com.duobei.core.auth.domain.Operator;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.helper.UserHelper;
import com.duobei.core.auth.service.OperatorService;
import com.duobei.core.sys.domain.SmsVerifyCode;
import com.duobei.core.sys.service.VerifyCodeService;
import com.duobei.dic.ZD;
import com.duobei.exception.TqException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;


/**
 * 自实现用户与权限查询. 密码用明文存储，因此使用默认 的SimpleCredentialsMatcher.
 */
public class MyShiroRealm extends AuthorizingRealm {
	private final static Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private OperatorService operatorService;
	@Autowired
	private VerifyCodeService verifyCodeService;


	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 通过登录账号保存用户信息
		Subject subject = SecurityUtils.getSubject();
		Session sessionLocal  = subject.getSession();
		log.info("获取用户信息>>>>>>>>>>>>>>>>>>>>>doGetAuthenticationInfo:" + subject.getSession().getId());
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		String userName = (String) token.getPrincipal();
		Operator operator = operatorService.queryOperatorByLoginName(userName);
		if (operator == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		OperatorCredential credential = UserHelper.createCredential(operator, GlobalConfig.getSuperAdmin());
		// 获取session并设置sessionId
		credential.setSessionId(sessionLocal.getId());

		String pwd = "";
		//使用短信验证码登录
		SmsVerifyCode smsVC = verifyCodeService.queryLastSmsVerifyCode(userName, ZD.notifyBizType_login);
		if (smsVC != null && System.currentTimeMillis() <= smsVC.getInvalidTime().getTime()) {
			pwd = smsVC.getVerifyCode();
		}

		//使用密码登录
		if(StringUtil.isBlank(pwd)){
			pwd = operator.getLoginPwd();
		}
		log.info("获取用户信息" + pwd + "===================>" + JSON.toJSONString(credential));
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(credential, // 用户
				pwd, // 密码
				operator.getLoginName() // realm name
		);
		// 若该方法什么都不做直接返回null的话,就会导致任何用户访问时都会自动跳转到unauthorizedUrl指定的地址
		return authenticationInfo;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		OperatorCredential credential = (OperatorCredential) principals.getPrimaryPrincipal();
		log.info("获取权限>>>>>>>>>>>>>doGetAuthorizationInfo");
		if (credential != null) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			if (credential.isSuperAdmin()) {
				authorizationInfo.addStringPermission("*");
			} else {
				// HashSet<String> roles=new HashSet<String>();
				// authorizationInfo.setRoles(roles);

				// 从数据库里查询用户的授权
				Set<String> pers = null;
				try {
					pers = operatorService.queryOperatorPermission(credential.getOpId());
				} catch (TqException e) {
					log.warn("查询用户权限异常", e);
				}
				System.out.println("===========》权限：" + pers);
				// HashSet<String> pers=new HashSet<String>();
				// pers.add("sys:menu:view");
				authorizationInfo.setStringPermissions(pers);
			}

			// 若该方法什么都不做直接返回null的话,就会导致任何用户访问时都会自动跳转到unauthorizedUrl指定的地址
			return authorizationInfo;
		}
		return null;
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		log.info("用户退出===清除用户凭证缓存");
		OperatorCredential key = (OperatorCredential) getAuthenticationCacheKey(principals);
		if (key != null && getAuthenticationCache() != null) {
			getAuthenticationCache().remove(key.getLoginName());
		}
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		log.info("用户退出===清除用户权限缓存");
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		log.info("用户退出===清除用户缓存");
		super.clearCache(principals);
	}
}
