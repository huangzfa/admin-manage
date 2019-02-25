package com.duobei.console.web.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duobei.console.web.controller.base.BaseController;

@Controller
@RequestMapping(value = "${authzPath}/sys/tree")
public class TreeController extends BaseController {

	@RequestMapping(value = "/data")
	public String list(HttpServletRequest request, Model model){
		model.addAttribute("url", request.getParameter("url"));
	    model.addAttribute("extId", request.getParameter("extId"));
	    model.addAttribute("checked", request.getParameter("checked"));
	    model.addAttribute("selectIds", request.getParameter("selectIds"));
	    model.addAttribute("isAll", request.getParameter("isAll"));
	    model.addAttribute("module", request.getParameter("module"));
	    return "sys/tree/treeData";
	}

}
