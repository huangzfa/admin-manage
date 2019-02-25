package com.duobei.console.web.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duobei.common.util.WebUtil;

@Controller
@RequestMapping(value = "/error")
public class ErrorController extends BaseController {
	/**
	 * 无权限访问时调用
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/unauthorized")
	public String unauthorized(HttpServletRequest req, HttpServletResponse resp) {
		//((Exception)req.getAttribute("exception")).printStackTrace();
		if (WebUtil.isAjax(req)) {
    		Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", 403);
			result.put("msg", "您没有足够的权限执行该操作!");
			writerJson(resp, result);
			return null;
		}else{
//			req.getAttribute("exception");//获取异常
			return "error/403";
		}
	}
	/**
	 * 404
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/404")
	public String notFindRes(HttpServletRequest req, HttpServletResponse resp) {
		if (WebUtil.isAjax(req)) {
    		Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", 404);
			result.put("msg", "您访问的资源不存在!");
			resp.setStatus(200);
			writerJson(resp, result);
			return null;
		}else{
			return "error/404";
		}
	}
	
	@RequestMapping(value = "/error")
	public String error(HttpServletRequest req, HttpServletResponse resp) {
		return "error/error";
	}
	
}
