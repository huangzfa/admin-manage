/*
package com.duobei.console.web.controller.app;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.app.domain.criteria.AppPageConfigCriteria;
import com.duobei.core.app.service.AppPageConfigService;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
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

@Controller
@RequestMapping(value = "${authzPath}/app/pageConfig")
public class AppPageConfigController extends  BaseController{
	private final static Logger log = LoggerFactory.getLogger(
      AppPageConfigController.class);

	private final static String PERMISSIONPRE = "app:pageConfig:";
	private final static String ADDRESSPRE = "app/pageConfig/";
	private final static String DESC = "应用页面";
	@Autowired
	private AppPageConfigService appPageConfigService;

	@RequiresPermissions(PERMISSIONPRE+"view")
	@RequestMapping(value = "/list")
	public String list() {
		return ADDRESSPRE+"appPageConfigList";
	}

	@RequiresPermissions(PERMISSIONPRE+"view")
	@ResponseBody
	@RequestMapping(value = "/appPageConfigList")
	public String appPageConfigList(AppPageConfigCriteria appPageConfigCriteria) {

		OperatorCredential credential = getCredential();
		if (appPageConfigCriteria.getPagesize() == 0) {
			appPageConfigCriteria.setPagesize(GlobalConfig.getPageSize());
		}
		try {
			ListVo<AppPageConfig> list = appPageConfigService.queryAppPageList(appPageConfigCriteria);
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			} else {
				log.warn("查询"+DESC+"列表异常", e);
				return failJsonResult("查询"+DESC+"列表异常，请查看错误日志");
			}
		}
	}

	@RequiresPermissions(PERMISSIONPRE+"view")
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute("apppage") AppPage appPage, Model model,RedirectAttributes redirectAttributes){
		try {
			if (appPage.getId() != null) {
				appPage = appPageService.queryAppPageById(appPage.getId());
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				log.warn("查询"+DESC+"异常", e);
				addFaildMessage(redirectAttributes, "查询"+DESC+"异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list";
		}
		model.addAttribute("apppage", appPage);
		return ADDRESSPRE+"appPageForm";
		}

	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	@RequestMapping(value = "/save")
	public String save(AppPage appPage, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			if (appPage.getId() == null) {
				appPageService.addAppPage(credential, appPage);
			} else {
				appPageService.updateAppPage(credential, appPage);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			} else {
				log.warn("save"+DESC+"异常", e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(appPage, model,redirectAttributes);
		}
		addMessage(redirectAttributes, "保存"+DESC+"成功!");
		return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list";
	}

	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	@RequestMapping(value = "/delete")
	public String delete(Integer id, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			appPageService.deleteAppPage(credential, id);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				log.warn("delete"+DESC+"异常", e);
				addFaildMessage(redirectAttributes, "删除"+DESC+"异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list";
		}
		addMessage(redirectAttributes, "删除"+DESC+"成功");
		return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list";
	}

}
*/
