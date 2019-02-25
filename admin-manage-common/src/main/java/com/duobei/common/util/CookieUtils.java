package com.duobei.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, "/",null,true,86400);//86400 为一天
	}
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		setCookie(response, name, value, "/",null,true,maxAge);
	}
	public static void setCookie(HttpServletResponse response, String name, String value,String path,String domain,boolean isHttpOnly,int maxAge) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(path);
		if(domain!=null){
			cookie.setDomain(domain);
		}
		cookie.setHttpOnly(isHttpOnly);
		cookie.setMaxAge(maxAge);//单位秒
		try {
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}
	public static void delCookie(HttpServletResponse response, String name){
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
	}
	public static String getCookie(HttpServletRequest request, String name) {
		return getCookie(request, null, name, false);
	}

	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		return getCookie(request, response, name, true);
	}

	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (isRemove) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return value;
	}
}
