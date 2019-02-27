package com.duobei.console.web.controller.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.auth.domain.vo.OrganRuleVo;
import com.duobei.core.manage.auth.service.OrganService;
import com.duobei.common.exception.TqException;

@Controller
@RequestMapping(value = "${authzPath}/sys/organRule")
public class OrganRuleController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(OrganRuleController.class);
	@Autowired
	private OrganService organService;
	
	@RequiresPermissions("sys:organRule:view")
	@RequestMapping(value = "/list")
	public String list(){
		return "sys/org/organRule/organRuleList";
	}
	@RequiresPermissions("sys:organRule:view")
	@ResponseBody
	@RequestMapping(value = "/allOrganRules")
	public String allOrganRules(HttpServletRequest request) {
		try {
			List<OrganRuleVo> list = organService.queryAllOrganRule();
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("查询组织架构规则异常",e);
				return failJsonResult("查询组织架构规则异常，请查看错误日志");
			}
		}
	}
	
	@RequiresPermissions("sys:organRule:view")
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute("organRule")OrganRuleVo organRule,Model model, RedirectAttributes redirectAttributes){
		try {
			if (organRule.getOrganRuleId()!=null) {
				organRule=organService.queryOrganRuleById(organRule.getOrganRuleId());
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("查询组织架构规则异常",e);
				addFaildMessage(redirectAttributes, "查询组织架构规则异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/organRule/list";
		}
		model.addAttribute("organRule", organRule);
	    return "sys/org/organRule/organRuleForm";
	}
	
	@RequiresPermissions({"sys:organRule:edit"})
	@RequestMapping(value = "/save")
	public String save(OrganRuleVo organRule, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		try {
			if (organRule.getOrganRuleId()==null) {
				organService.addOrganRule(credential,organRule);
			}else{
				organService.updateOrganRule(credential,organRule);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			}else{
				log.warn("save组织架构规则异常",e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(organRule, model,redirectAttributes);
		}
		addMessage(redirectAttributes, "保存组织架构规则成功!");
		return "redirect:" + this.authzPath + "/sys/organRule/list";
	}
	
	@RequiresPermissions({"sys:organRule:edit"})
	@RequestMapping(value = "/delete")
	public String delete(Integer organRuleId, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		try {
			organService.deleteOrganRule(credential,organRuleId);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("delete组织架构规则异常",e);
				addFaildMessage(redirectAttributes, "删除组织架构规则异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/organRule/list";
		}
		addMessage(redirectAttributes, "删除组织架构规则成功");
		return "redirect:" + this.authzPath + "/sys/organRule/list";
	}
}
