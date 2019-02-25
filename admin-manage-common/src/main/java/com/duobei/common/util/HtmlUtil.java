package com.duobei.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class HtmlUtil {
	// WriteMapNullValue——–是否输出值为null的字段,默认为false
	// WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
	// WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
	// WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
	// WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
	public static final SerializerFeature[] sfs1 = { SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteDateUseDateFormat };
	public static final SerializerFeature[] sfs2 = { SerializerFeature.WriteNullStringAsEmpty };

	public static void writerJson(HttpServletResponse response, String jsonStr) {
		response.setContentType("application/json;charset=utf-8");
		writer(response, jsonStr);
	}

	public static void writerJson(HttpServletResponse response, Object object) {
		try {
			response.setContentType("application/json;charset=utf-8");
			writer(response, JSON.toJSONString(object));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void writerHtml(HttpServletResponse response, String htmlStr) {
		writer(response, htmlStr);
	}

	public static void writer(HttpServletResponse response, String str) {
		PrintWriter out = null;
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(str);
			out.flush();//或者使用response.flushBuffer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
		
	}
}
