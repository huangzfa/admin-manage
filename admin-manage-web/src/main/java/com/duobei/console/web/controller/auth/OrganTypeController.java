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
import com.duobei.core.manage.auth.domain.OrganType;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.auth.service.OrganService;
import com.duobei.common.exception.TqException;

@Controller
@RequestMapping(value = "${authzPath}/sys/organType")
public class OrganTypeController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(OrganTypeController.class);
	@Autowired
	private OrganService organService;
	
	@RequiresPermissions("sys:organType:view")
	@RequestMapping(value = "/list")
	public String list(){
		return "sys/org/organType/organTypeList";
	}
	@RequiresPermissions("sys:organType:view")
	@ResponseBody
	@RequestMapping(value = "/allOrganTypes")
	public String allOrganTypes(HttpServletRequest request) {
		try {
			List<OrganType> list = organService.queryAllOrganType();
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("查询组织类型异常",e);
				return failJsonResult("查询组织类型异常，请查看错误日志");
			}
		}
	}
	
	@RequiresPermissions("sys:organType:view")
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute("organType")OrganType organType,Model model, RedirectAttributes redirectAttributes){
		try {
			if (organType.getOrganTypeId()!=null) {
				organType=organService.queryOrganTypeById(organType.getOrganTypeId());
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("查询组织类型异常",e);
				addFaildMessage(redirectAttributes, "查询组织类型异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/organType/list";
		}
		model.addAttribute("organType", organType);
	    return "sys/org/organType/organTypeForm";
	}
	@RequiresPermissions({"sys:organType:edit"})
	@RequestMapping(value = "/save")
	public String save(OrganType organType, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		try {
			if (organType.getOrganTypeId()==null) {
				organService.addOrganType(credential,organType);
			}else{
				organService.updateOrganType(credential,organType);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			}else{
				log.warn("save组织类型异常",e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(organType, model,redirectAttributes);
		}
		addMessage(redirectAttributes, "保存组织类型【" + organType.getOrganTypeName() + "】成功!");
		return "redirect:" + this.authzPath + "/sys/organType/list";
	}
	
	@RequiresPermissions({"sys:organType:edit"})
	@RequestMapping(value = "/delete")
	public String delete(Integer organTypeId, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		try {
			organService.deleteOrganType(credential,organTypeId);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("delete组织类型异常",e);
				addFaildMessage(redirectAttributes, "删除组织类型异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/organType/list";
		}
		addMessage(redirectAttributes, "删除组织类型成功");
		return "redirect:" + this.authzPath + "/sys/organType/list";
	}
	
	@RequiresPermissions("sys:organType:view")
	@RequestMapping(value = "/selectOrganType")
	public String toSelectOrganType(Integer organTypeId,Model model) {
		if (organTypeId!=null) {
			model.addAttribute("organTypeId", organTypeId);
		}
		return "sys/org/organType/selectOrganType";
	}
}
