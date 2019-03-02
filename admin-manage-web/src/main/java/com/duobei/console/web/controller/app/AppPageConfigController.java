
package com.duobei.console.web.controller.app;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.encrypt.MD5Util;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.criteria.AppPageConfigCriteria;
import com.duobei.core.operation.app.service.AppPageConfigService;

import java.util.*;

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
import sun.security.provider.MD5;

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
		    //TODO 用户权限
//			List<App> appList = credential.getAppList();
            List<App> appList = new ArrayList<>();
            App app1 = new App();
            app1.setAppName("美期钱包");
            app1.setId(1);
            app1.setAppKey("mqqb");
            appList.add(app1);

            App app2 = new App();
            app2.setAppName("巧花");
            app2.setId(2);
            app2.setAppKey("qh");
            appList.add(app2);
            if (appList == null){
				return failJsonResult("应用权限不足");
			}
			//给与默认查询的应用
			if (StringUtil.isEmpty(appPageConfigCriteria.getAppId())){
				appPageConfigCriteria.setAppId(appList.get(0).getId());
			}
			ListVo<AppPageConfig> list = appPageConfigService.queryAppPageConfigList(appPageConfigCriteria);
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("list",list);
			//查询条件-审批状态
			dataMap.put("appList",appList);
			return successJsonResult(dataMap,"success");
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
	public String form(@ModelAttribute("appPageConfig") AppPageConfig appPageConfig, Model model,RedirectAttributes redirectAttributes){
		try {
			if (appPageConfig.getId() != null) {
                appPageConfig = appPageConfigService.queryAppPageConfigById(appPageConfig.getId());
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				log.warn("查询"+DESC+"异常", e);
				addFaildMessage(redirectAttributes, "查询"+DESC+"异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list?appId="+appPageConfig.getAppId();
		}
 		model.addAttribute("appPageConfig", appPageConfig);
		return ADDRESSPRE+"appPageConfigForm";
		}

	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	@RequestMapping(value = "/save")
	public String save(AppPageConfig appPageConfig, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			/**
			 * 暂时未默认值
			 */
			appPageConfig.setProductId(0);
			appPageConfig.setIsEnable(0);
			appPageConfig.setMenuType(2);
			appPageConfig.setIsExamine(0);
			appPageConfig.setMenuCode(MD5Util.encrypt(appPageConfig.getId()+appPageConfig.getMenuName()));
			/**/
			appPageConfig.setModifyOperatorId(credential.getOpId());
			appPageConfig.setModifyTime(new Date());
			if (appPageConfig.getId() == null) {
				appPageConfig.setAddOperatorId(credential.getOpId());
				appPageConfig.setAddTime(appPageConfig.getModifyTime());
				appPageConfigService.addAppPageConfig(appPageConfig);
			} else {
                appPageConfigService.updateAppPageConfig(appPageConfig);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			} else {
				log.warn("save"+DESC+"异常", e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(appPageConfig, model,redirectAttributes);
		}
		addMessage(redirectAttributes, "保存"+DESC+"成功!");
		return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list?appId="+appPageConfig.getAppId();
	}

	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	@RequestMapping(value = "/updateStatus")
	public String updateStatus(Integer id,Integer appId ,Integer isEnable, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			AppPageConfig appPageConfig = new AppPageConfig();
			appPageConfig.setId(id);
			appPageConfig.setModifyTime(new Date());
			appPageConfig.setModifyOperatorId(credential.getOpId());
			appPageConfig.setIsEnable(isEnable);
			appPageConfigService.updateIsEnable(appPageConfig);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				log.warn("update isEnable"+DESC+"异常", e);
				addFaildMessage(redirectAttributes, "启用/禁用"+DESC+"异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list?appId="+appId;
		}
		addMessage(redirectAttributes, "启用/禁用"+DESC+"成功");
		return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list?appId="+appId;
	}


	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	@RequestMapping(value = "/delete")
	public String delete(Integer id, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			AppPageConfig appPageConfig = new AppPageConfig();
			appPageConfig.setId(id);
			appPageConfig.setModifyTime(new Date());
			appPageConfig.setModifyOperatorId(credential.getOpId());
			appPageConfigService.deleteAppPageConfig(appPageConfig);
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

