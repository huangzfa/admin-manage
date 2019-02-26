package com.duobei.console.web.controller.auth;

import com.duobei.cache.CacheFactory;
import com.duobei.common.cache.CacheHelper;
import com.duobei.common.servlet.ValidateCodeServlet;
import com.duobei.common.util.Constants;
import com.duobei.common.util.RegExpValidatorUtils;
import com.duobei.common.util.WebUtil;
import com.duobei.common.util.encrypt.PasswordUtil;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.console.shiro.UsernamePasswordCaptchaToken;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.auth.domain.Operator;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.service.OperatorLoginLogService;
import com.duobei.core.auth.service.OperatorService;
import com.duobei.core.sys.service.VerifyCodeService;
import com.duobei.dic.ZD;
import com.duobei.common.exception.TqException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class LoginController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(LoginController.class);

	public final static String isNeedVerifyCodeKey = "isNeedVerifyCode";


	@Autowired
	private OperatorService operatorService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private OperatorLoginLogService operatorLoginLogService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.registerCustomEditor(Long.class, "price", new
		// StringPriceToLong());
	}

	@ResponseBody
	@RequestMapping(value = "/login/send/sms")
	public String loginCode(String mn, String ivc, HttpServletRequest req) throws RuntimeException {
		if (StringUtils.isBlank(mn)) {
			return failJsonResult("手机号不能为空");
		}
		if (StringUtils.isBlank(ivc)) {
			return failJsonResult("图形验证码不能为空");
		}
		if (!ivc.equalsIgnoreCase((String) req.getSession().getAttribute(ValidateCodeServlet.VALIDATE_CODE))) {
			return failJsonResult("图形验证码错误");
		}
		if (!RegExpValidatorUtils.isMobile(mn)) {
			return failJsonResult("无效的手机号");
		}
		Operator operator = operatorService.queryOperatorByLoginName(mn);
		if (operator == null) {
			return failJsonResult("账号或手机号不存在");
		}
		verifyCodeService.sendSms(mn, ZD.notifyBizType_login);
		return simpleSuccessJsonResult("ok");
	}

	// 跳转到登录页
	@RequestMapping(value = "/tologin")
	public String toLogin(HttpServletRequest request) {
		return "sys/login";
	}

	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		String error = "登录失败";
		UsernamePasswordCaptchaToken token = null;
		Subject subject = getSubject();
		String username = WebUtils.getCleanParam(request, "username");
		if (StringUtils.isBlank(username)) {
			return failJsonResult("账号号不能为空");
		}
		String password = WebUtils.getCleanParam(request, "password");
		try {
			//记住密码
			boolean rememberMe = WebUtils.isTrue(request, "rememberMe");
			String host = request.getRemoteHost();
			String validateCode = WebUtils.getCleanParam(request, "validateCode");
			Operator operator = operatorService.queryOperatorByLoginName(username);
			if (operator == null) {
				return failJsonResult("账号或手机号不存在");
			}
			// 特殊处理，开发时便捷登录
			if (StringUtils.isNotEmpty(password)) {
				//校验登录密码是否正确
				Boolean encryPass = PasswordUtil.verifyPwd(password, operator.getLoginPwd());
				if (!encryPass) {
					return failJsonResult("密码错误");
				}
				//检测是不是第一次登录
				//if (operatorLoginLogService.loginFirst(operator.getOpId())) {
					//密码没有重置
					if (PasswordUtil.verifyPwd(password, PasswordUtil.encryptPwdForSalt(Constants.LOGIN_PASSWORD))) {
						return failJsonResult("error", "loginFirst", operator);
					}
				//}
				validateCode = operator.getLoginPwd();
			} else {
				verifyCodeService.checkVerifyCode(username, validateCode, ZD.notifyBizType_login);
			}

			token = new UsernamePasswordCaptchaToken(username, validateCode.toCharArray(), rememberMe, host);
			subject.login(token);
			//标记其他此账号在其它地方登录
			List<Session> loginedList = getLoginedSession(subject);
			for (Session session : loginedList) {
				session.setAttribute("forceLogout","1");
				session.setAttribute("tickTime", DateUtil.format_yyyy_MM_ddHHmmss(new Date()));
			}
			OperatorCredential credential = getCredential();
			credential.setIp(WebUtil.getRemoteIP(request));
			Session shiroSession = subject.getSession();
			credential.setSessionCreateTime(shiroSession.getStartTimestamp());
			credential.setSessionTimeout(shiroSession.getTimeout());

			// 登录成功后更新信息
			operatorService.updateOperatorForLogin(credential);
			// 登录后记录日志
			operatorLoginLogService.save(credential, ZD.loginType_in);

			return successJsonResult("success", "mainUrl", this.authzPath);
		} catch (Exception e) {
			error = "登录异常";
			if ((e instanceof IncorrectCredentialsException) || (e instanceof UnknownAccountException)) {
				error = "账号或验证码错误";
			} else if (e instanceof TqException) {
				error = e.getMessage();
			} else {
				log.error("登录异常", e);
			}
		}
		if (token != null) {
			token.clear();
		}
		return failJsonResult(error);
	}

	//遍历同一个账户的session
	private List<Session> getLoginedSession(Subject currentUser) {
		Collection<Session> list = ((DefaultSessionManager) ((DefaultSecurityManager) SecurityUtils
				.getSecurityManager()).getSessionManager()).getSessionDAO()
				.getActiveSessions();
		List<Session> loginedList = new ArrayList<>();
		OperatorCredential loginUser = (OperatorCredential) currentUser.getPrincipal();
		for (Session session : list) {
			Subject s = new Subject.Builder().session(session).buildSubject();
			if (s.isAuthenticated()) {
				OperatorCredential user = (OperatorCredential) s.getPrincipal();

				if (user.getOpId().equals(loginUser.getOpId())) {
					if (!session.getId().equals(
							currentUser.getSession().getId())) {
						loginedList.add(session);
					}
				}
			}
		}
		return loginedList;
	}
	@RequestMapping(value = "${authzPath}")
	public String main(Model model) {
		OperatorCredential credential = getCredential();
		isNeedVerifyCode(credential.getLoginName(), true, true);
		model.addAttribute("menus", operatorService.queryMenuByOperator(credential));
		model.addAttribute("user", credential);

		return "sys/main";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) throws TqException {

		Subject subject = getSubject();
		// 登出记录日志
		OperatorCredential credential = getCredential();
		credential.setIp(WebUtil.getRemoteIP(request));
		credential.setSessionId(credential.getSessionId());
		credential.setOpId(credential.getOpId());
		operatorLoginLogService.save(credential, ZD.loginType_out);
		subject.logout();
		return "redirect:/";
	}

	@RequestMapping(value = "/kickout")
	public String kickout(HttpServletRequest request,Model model) throws TqException {
		HttpSession session = request.getSession();
		Object tickTime =session.getAttribute("tickTime");
		if( tickTime!=null){
			model.addAttribute("tickTime",tickTime);
			//销毁session
			session.setMaxInactiveInterval(1);
		}
		return "/sys/406";
	}

	@RequestMapping(value = "/modifyPwd")
	public String updatePassSendSms(String phone,Integer opId,Model model) throws TqException {
		model.addAttribute("phone",phone);
		model.addAttribute("opId",opId);
		if( !StringUtil.isBlank(phone) && phone.length()==11){
			model.addAttribute("mobile",phone.substring(0,3)+"****"+phone.substring(7,11));
		}
		return "/sys/modifyPwd";
	}

	/**
	 * 第一次登录修改密码密码发送验证码
	 *
	 * @param mobile
	 * @param req
	 * @return
	 * @throws RuntimeException
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePass/send/sms")
	public String updatePassSendSms(String mobile, HttpServletRequest req) throws TqException {
		if (StringUtils.isBlank(mobile)) {
			return failJsonResult("手机号不能为空");
		}
		if (!RegExpValidatorUtils.isMobile(mobile)) {
			return failJsonResult("无效的手机号");
		}
		Operator operator = operatorService.queryOperatorByLoginName(mobile);
		if (operator == null) {
			return failJsonResult("账号或手机号不存在");
		}
		verifyCodeService.sendSms(mobile, ZD.notifyBizType_update);
		return simpleSuccessJsonResult("ok");
	}

	/**
	 * 第一次登录修改密码
	 *
	 * @param mobile
	 * @param newPassword
	 * @return
	 * @throws RuntimeException
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePass")
	public String updatePass(Integer opId,String mobile,  String newPassword,String code) throws TqException {
		if (StringUtils.isBlank(mobile)) {
			return failJsonResult("手机号不能为空");
		}
		if (!RegExpValidatorUtils.isMobile(mobile)) {
			return failJsonResult("无效的手机号");
		}
		Operator operator = operatorService.queryOperatorByLoginName(mobile);
		if (operator == null) {
			return failJsonResult("账号或手机号不存在");
		}
		try {
			newPassword = URLDecoder.decode(newPassword, "UTF-8");
			verifyCodeService.checkVerifyCode(mobile, code, ZD.notifyBizType_update);
			operatorService.modifyLoginPwd(opId, Constants.LOGIN_PASSWORD, newPassword);
			return simpleSuccessJsonResult("success");
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			} else {
				log.warn("修改密码异常", e);
				return failJsonResult("修改密码异常，请查看错误日志");
			}
		}
	}

	/**
	 * 是否需要验证码
	 * 
	 * @param username
	 *            登录名
	 * @param loginFlag
	 *            登录是否成功标志，，true成功，false失败
	 * @return
	 */
	private boolean isNeedVerifyCode(String username, boolean loginFlag, boolean isClean) {
		try {
			CacheHelper sysCache = CacheFactory.getSysCache();
			// 如果登录失败，计数器加一
			if (!loginFlag) {
				AtomicInteger count = (AtomicInteger) sysCache.getVal(username);
				if (count == null) {
					count = new AtomicInteger(0);
				}
				count.getAndIncrement();
				sysCache.put(username, count);
				if (count.get() >= 3) {
					return true;
				}
			}
			if (isClean) {
				sysCache.remove(username);
			}
		} catch (Exception e) {
		}
		return false;
	}
}
