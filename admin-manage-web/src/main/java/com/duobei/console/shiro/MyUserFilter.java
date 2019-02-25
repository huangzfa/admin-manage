package com.duobei.console.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyUserFilter extends AccessControlFilter {
	private final static Logger log = LoggerFactory.getLogger(MyUserFilter.class);
	public MyUserFilter() {
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception{
		HttpServletRequest req=(HttpServletRequest) request;
		log.info("执行MyUserFilter===========>>>isAccessAllowed【"+req.getRequestURI()+"】");
		if (isLoginRequest(request, response)) {
			log.info("MyUserFilter===========>>>isAccessAllowed-登录请求不拦截-返回true");
            return true;
        } else {
            Subject subject = getSubject(request, response);
            Session session = subject.getSession();
            //如果标记存在，销毁会话
            if (session!=null && "1".equals(session.getAttribute("forceLogout"))) {
                Object tickTime = session.getAttribute("tickTime");
                try {
                    session.removeAttribute("forceLogout");
                    session.removeAttribute("tickTime");
                    session.stop();
                } catch (Exception e) {

                }
                HttpSession session1 = req.getSession();
                session1.setAttribute("tickTime",tickTime);
                WebUtils.issueRedirect(req, response, "/kickout");
                log.info("MyUserFilter===========>>>onAccessDenied-某种原因被踢出-返回false");
                return false;
            }
            if(subject.getPrincipal() == null){
            	log.info("MyUserFilter===========>>>isAccessAllowed-账号不存在-返回false");
            	return false;
            }
            
            String[] rolesArray = (String[]) mappedValue;
            if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问 
            	log.info("MyUserFilter===========>>>isAccessAllowed-没有角色限制，有权限访问-返回true");
                return true;
            }
            for (int i = 0; i < rolesArray.length; i++) {  
                if (subject.hasRole(rolesArray[i])) { //若当前用户是rolesArray中的任何一个，则有权限访问 
                	log.info("MyUserFilter===========>>>isAccessAllowed-符合【"+rolesArray[i]+"】角色-返回true");
                    return true;
                }  
            }  
        }
		log.info("MyUserFilter===========>>>isAccessAllowed-返回false");
        return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws IOException {
		log.info("执行MyUserFilter===========>>>onAccessDenied");

		Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
			WebUtils.issueRedirect(request, response, "/");
        	log.info("MyUserFilter===========>>>onAccessDenied-未登录跳转到登录页-返回false");
        	return false;
        }else{
            Session session = subject.getSession();
            //如果标记存在，销毁会话
            if (session!=null && "1".equals(session.getAttribute("forceLogout"))) {
                Object tickTime = session.getAttribute("tickTime");
                try {
                    session.removeAttribute("forceLogout");
                    session.removeAttribute("tickTime");
                    session.stop();
                } catch (Exception e) {

                }
                HttpServletRequest req=(HttpServletRequest) request;
                HttpSession session1 = req.getSession();
                session1.setAttribute("tickTime",tickTime);
                WebUtils.issueRedirect(req, response, "/kickout");
                log.info("MyUserFilter===========>>>onAccessDenied-某种原因被踢出-返回false");
                return false;
            }
        }
        log.info("执行MyUserFilter===========>>>onAccessDenied-返回true");
        return true;
	}
}
