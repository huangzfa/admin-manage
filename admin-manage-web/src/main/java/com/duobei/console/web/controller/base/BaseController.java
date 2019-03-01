package com.duobei.console.web.controller.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.product.domain.Product;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.duobei.common.util.HtmlUtil;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;

public class BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	public static SerializerFeature[] sf = { SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteDateUseDateFormat };

	/** -- 请求成功时默认的文案 -- */
	private static final String SUCCESS_MSG = "成功";
	/** -- 请求失败时默认的文案-- */
	private static final String ERROR_MSG = "失败";

	/** -- 请求成功时默认的code -- */
	private static final Integer SUCCESS_CODE = 1;
	/** -- 请求失败时默认的code -- */
	private static final Integer ERROR_CODE = 0;
	/** -- 人审失败时的code -- */
	public static final Integer AUDIT_ERROR_CODE = 1001;
	/** -- 人审成功时的code -- */
	public static final Integer AUDIT_SUCCESS_CODE = 1000;

	@Value("${authzPath}")
	protected String authzPath;

	protected Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	protected OperatorCredential getCredential() {
		Subject subject = getSubject();
		OperatorCredential myCredential = (OperatorCredential) subject.getPrincipal();
		return myCredential;
	}

	protected String get404Page() {
		return "error/404";
	}

	protected String get500Page() {
		return "error/500";
	}

	protected String getErrorPage() {
		return "error/error";
	}

	protected void addMessage(Model model, String[] messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
	}

	protected void addMessage(Model model, String messages) {
		model.addAttribute("message", messages);
	}

	protected void addFaildMessage(Model model, String messages) {
		model.addAttribute("message", ERROR_MSG + messages);
	}

	protected void addMessage(RedirectAttributes redirectAttributes, String[] messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}

	protected void addMessage(RedirectAttributes redirectAttributes, String messages) {
		redirectAttributes.addFlashAttribute("message", messages);
	}

	protected void addFaildMessage(RedirectAttributes redirectAttributes, String messages) {
		redirectAttributes.addFlashAttribute("message", ERROR_MSG + messages);
	}

	/**
	 * 验证数据权限
	 * @param params
	 * @throws TqException
	 */
	protected void validAuthData  (Integer... params) throws TqException{
		OperatorCredential credential = getCredential();
		if( credential == null ){
			throw  new TqException("登录过期，请重新登录");
		}
		if ( credential.getProductList() == null ){
			return;
		}
		if( params.length > 0  && params[0]!=null){
			List<Integer> list = credential.getProductList().stream().map(Product::getId).collect(Collectors.toList());
			if( !list.contains(params[0]) ){
				throw  new TqException("您没有改产品的操作权限");
			}
		}
		if( params.length > 1  && params[1]!=null){
			List<Integer> list = credential.getAppList().stream().map(App::getId).collect(Collectors.toList());
			if( !list.contains(params[1]) ){
				throw  new TqException("您没有改应用的操作权限");
			}
		}
	}

	/**
	 * 获取request中postData中的数据
	 * 
	 * @param req
	 * @return
	 */
	public String getPostData(HttpServletRequest req, String character) {
		InputStream is = null;
		try {
			is = req.getInputStream();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(is, StringUtils.isBlank(character) ? "UTF-8" : character));
			// 读取HTTP请求内容
			String buffer = null;
			StringBuffer sb = new StringBuffer();
			while ((buffer = br.readLine()) != null) {
				sb.append(buffer);
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
				is = null;
			}
		}
	}

	public void writerJson(HttpServletResponse resp, Object result) {
		HtmlUtil.writerJson(resp, result);
	}

	public void writerHtml(HttpServletResponse resp, String htmlStr) {
		HtmlUtil.writerHtml(resp, htmlStr);
	}

	public String jsonResult(Object result) {
		return JSON.toJSONString(result, sf);
	}

	public String simpleSuccessJsonResult() {
		return simpleSuccessJsonResult(SUCCESS_MSG);
	}

	public String simpleSuccessJsonResult(String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", SUCCESS_CODE);
		result.put("msg", msg);
		return JSON.toJSONString(result, sf);
	}

	public String simpleSuccessJsonResult(String msg,Integer code) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", msg);
		return JSON.toJSONString(result, sf);
	}

	public String successJsonResult(String msg, String key, Object val) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", SUCCESS_CODE);
		result.put("msg", msg);
		result.put(key, val);
		return JSON.toJSONString(result, sf);
	}

	public String successJsonResult(Map<String, Object> result, String msg) {
		result.put("code", SUCCESS_CODE);
		result.put("msg", msg);
		return JSON.toJSONString(result, sf);
	}

	public String successJsonResult(Map<String, Object> result) {
		return successJsonResult(result, SUCCESS_MSG);
	}

	public String simpleFailJsonResult() {
		return failJsonResult(ERROR_MSG);
	}

	public void failJsonResult(HttpServletResponse resp, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", ERROR_CODE);
		result.put("msg", msg);
		writerJson(resp, result);
	}

	public String failJsonResult(String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", ERROR_CODE);
		result.put("msg", msg);
		return JSON.toJSONString(result, sf);
	}

	public String failJsonResult(String msg,Integer code) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", msg);
		return JSON.toJSONString(result, sf);
	}

	public String failJsonResult(String msg, String key, Object val) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", ERROR_CODE);
		result.put("msg", msg);
		result.put(key, val);
		return JSON.toJSONString(result, sf);
	}

	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public String param(String name, String... def_value) {
		String v = getRequest().getParameter(name);
		return (v != null) ? v : ((def_value.length > 0) ? def_value[0] : null);
	}
	/**
	 * 输出信息到浏览器
	 * @param msg
	 * @throws IOException
	 */
	public static void print(HttpServletResponse response, Object msg)  {
		if (!"UTF-8".equalsIgnoreCase(response.getCharacterEncoding())){
			response.setCharacterEncoding("UTF-8");
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		try{
			response.getWriter().print(msg);

		}catch (IOException e){
			logger.info("======== 输出异常 "+e);
		}
	}
	public Integer paramInt(String name, Integer... def_value) {
		String v = param(name);
		Integer _v = (def_value.length > 0) ? def_value[0] : null;
		if( v == null ) return _v;
		try {
			_v = Integer.parseInt(v);
		} catch (Exception e) {}
		return _v;
	}
}