package com.duobei.console.web.interceptor;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor extends HandlerInterceptorAdapter {
	private final static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

	public RequestInterceptor(){
	}
	//利用正则匹配需要拦截的路径
	private Pattern pattern = Pattern.compile("^/test/.*|^/un$|[a-zA-Z0-9_]*\\.jsp$");
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
//	@Autowired
//	private AuthService authService;
	private String toLogin="/toLogin.do";
//	private String toLoginForSessionTimeout="toLoginForSessionTimeout";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();// /yffood/main.do
		String contextPath = request.getContextPath();// /yffood
		String url = requestUri.substring(contextPath.length());// /main.do
		if (pattern.matcher(url).matches()) {
			log.info("==============================>:"+url);
		}
		return true;
		/**
		//可以看到 Ajax 请求多了个 x-requested-with ，可以利用它，request.getHeader("x-requested-with"); 为 null，则为传统同步请求，为 XMLHttpRequest，则为 Ajax 异步请求。
		if (authService.isCommonResource(url)) {
			log.info("公共资源url:" + url);
			return true;
		}
		Credential credential = (Credential) request.getSession(true).getAttribute(Credential.Credential_Key);
		if (credential == null) {
			// 如果用户未登录，则跳转到登录页
			log.info("未登录，无权访问：" + url);
			gotoLoginPage(request, response);
			return false;
		} else {
			if (credential.getIsSuperAdmin()) {
				return true;
			}
			if (StringUtils.isBlank(url)) {
				redirectPage(response, request.getContextPath() + "/manage.do");
				return false;
			}
			if(credential.getUserResource().contains(url)){
				return true;
			}else{
				if (authService.isCommonResourceForLogin(url)) {
					log.info("需要登录的公共资源url:" + url);
					return true;
				}
				unAuthRedirectPage(request, response, url);
				return false;
			}
		}
		*/
	}
	public void gotoLoginPage(HttpServletRequest request, HttpServletResponse response){
		if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", -1);
			result.put("msg", request.getContextPath() + toLogin);
//			HtmlUtil.writerJson(response, result);
		}else{
			redirectPage(response, request.getContextPath() + toLogin);
		}
	}
	public void unAuthRedirectPage(HttpServletRequest request, HttpServletResponse response, String url) {
		log.info("无权访问：>" + url+"<");
		if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", 0);
			result.put("msg", "权限不足!");
//			HtmlUtil.writerJson(response, result);
		}else{
			redirectPage(response, request.getContextPath() + "/403.html");
		}
	}

	public void redirectPage(HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
		}
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	// @Override
	// public void postHandle(HttpServletRequest request, HttpServletResponse
	// response, Object handler,
	// ModelAndView modelAndView) throws Exception {
	// log.info("==============执行顺序: 2、postHandle================");
	// }

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	// @Override
	// public void afterCompletion(HttpServletRequest request,
	// HttpServletResponse response, Object handler, Exception ex)
	// throws Exception {
	// log.info("==============执行顺序: 3、afterCompletion================");
	// }
	
	public static void main(String[] args) throws Exception {
		String str="^/test/.*|^/un$|[a-zA-Z0-9_]*\\.jsp$";
		System.out.println(URLEncoder.encode(str, "UTF-8"));
		System.out.println(str);
		System.out.println(URLDecoder.decode(URLEncoder.encode(str, "UTF-8"), "UTF-8"));
		Pattern p = Pattern.compile(str);
		System.out.println(p.matcher("/test/page").matches());
		System.out.println(p.matcher("/bb/page").matches());
		System.out.println(p.matcher("/un").matches());
		System.out.println(p.matcher("a_3A.jsp").matches());
	}
}
