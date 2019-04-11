package com.duobei.console.web.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/test")
public class TestController extends com.duobei.console.web.controller.base.BaseController {
	private final static Logger log = LoggerFactory.getLogger(TestController.class);
//	当你想在controller的映射requestMapping之间进行跳转一定要加前缀：foward  或者redirect
	
	@RequestMapping(value = "/testPage")
	public ModelAndView testPage(HttpServletRequest req, HttpServletResponse resp) {
		// 1、收集参数、验证参数
		// 2、绑定参数到命令对象
		// 3、将命令对象传入业务对象进行业务处理
		// 4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		//<!-- 当controller返回的页面有jsp、html等多种格式时，suffix可以去掉，在controller返回的ModelAndView写为xxx.jsp或xxx.html -->
//		mv.setViewName("test/test.html");
		mv.setViewName("test/main");
		return mv;
	}
	@RequestMapping(value = "/mainPage")
	public String mainPage(HttpServletRequest req, HttpServletResponse resp) {
		//<!-- 当controller返回的页面有jsp、html等多种格式时，suffix可以去掉，在controller返回的ModelAndView写为xxx.jsp或xxx.html -->
		return "test/one";
	}

	@RequestMapping(value = "/json1")
	public void json(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("key1", "val1");
		result.put("key2", "中文啊啊啊");
		writerJson(resp, result);
	}
	@ResponseBody
	@RequestMapping(value = "/json2")
	public String lock1(HttpServletRequest req, HttpServletResponse resp) {
		try {
			return simpleSuccessJsonResult("洪捃能");
		} catch (Exception e) {
			log.warn("",e);
			return failJsonResult(e.getMessage());
		}
	}
	@ResponseBody
	@RequestMapping(value = "/json3", produces = "application/json; charset=utf-8")
	public Object lock2(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("msg", "小洪");
		return result;
	}
	/**
	@ResponseBody
	@RequestMapping(value = "/area")
	public String area(HttpServletRequest req, HttpServletResponse resp) {
		try {
			dgArea(0l);
			return simpleSuccessJsonResult("ok");
		} catch (Exception e) {
			log.warn("",e);
			return failJsonResult(e.getMessage());
		}
	}
	public void dgArea(long parentId){
		log.info(parentId+"  =============================================================");
		try {
			String result=queryArea(parentId);
			log.info("查询结果："+result);
			JSONObject resultObj=JSON.parseObject(result).getJSONObject("showapi_res_body");
			boolean flag=resultObj.getBooleanValue("flag");
			if(flag){
				JSONArray arr=resultObj.getJSONArray("data");
				for (int i = 0; i < arr.size(); i++) {
					JSONObject one=arr.getJSONObject(i);
					Area ar=new Area();
					try {
						ar.setId(one.getLong("id"));
						ar.setParentId(one.getLong("parentId"));
						ar.setProvinceId(one.getLong("provinceId"));
						ar.setCityId(one.getLong("cityId"));
						ar.setCountyId(one.getLong("countyId"));
						ar.setAreaCode(one.getString("areaCode"));
						ar.setZipCode(one.getString("zipCode"));
						ar.setAreaName(one.getString("areaName"));
						ar.setSimpleName(one.getString("simpleName"));
						ar.setWholeName(one.getString("wholeName"));
						ar.setPinYin(one.getString("pinYin"));
						ar.setSimplePy(one.getString("simplePy"));
						ar.setPrePinYin(one.getString("prePinYin"));
						ar.setLevel(one.getInteger("level"));
						ar.setLon(one.getBigDecimal("lon"));
						ar.setLat(one.getBigDecimal("lat"));
						ar.setRemark(one.getString("remark"));
						ar.setAreaSort(1);
						if(ar.getLevel()==4){
							areaMapper.insertSelective(ar);
						}
					} catch (Exception e) {
						log.error("插入省市区异常",e);
					}
					if(ar.getLevel()<=3){
						dgArea(ar.getId());
					}
				}
			}
		} catch (Exception e) {
			log.warn("额外异常",e);
		}
	}
	public String queryArea(long parentId){
		try {
			String host = "http://ali-city.showapi.com";
		    String path = "/areaDetail";
		    String method = "GET";
		    String appcode = "9969258ff9fb4c66b4e3bd8e7e9c85af";
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("parentId", String.valueOf(parentId));
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
}
