package com.duobei.console.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFilter extends AccessControlFilter {
	private final static Logger log = LoggerFactory.getLogger(SessionFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
		log.debug("=======================SessionFilter---onAccessDenied");
		Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }
        Session session = subject.getSession();
        //1,根据业务设置标记，，比如被踢出或最多同时在线2个人
        
        //2，然后判断标记，如果符合某个标记就跳转到登录页并提醒
        if (session.getAttribute("xxxx") != null) {
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response, "登录页url?原因");
            return false;
        }
        
		return true;
	}

}
