package com.duobei.console.web.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duobei.console.web.controller.base.BaseController;

@Controller
@RequestMapping(value = "${authzPath}/sys/icon")
public class IconController extends BaseController {

	@RequestMapping(value = "/data")
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "sys/icon/iconList";
	}
}