
package com.duobei.console.web.controller.app;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.encrypt.MD5Util;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.operation.app.domain.criteria.AppPageConfigCriteria;
import com.duobei.core.operation.app.service.AppPageConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @author huocai
 * @description
 * @date 2019/2/26
 */

@Controller
@RequestMapping(value = "${authzPath}/app/pageConfig")
@Slf4j
public class AppPageConfigController extends  BaseController{

	private final static String PERMISSIONPRE = "app:pageConfig:";
	private final static String ADDRESSPRE = "app/pageConfig/";
	private final static String DESC = "应用页面";
	@Autowired
	private AppPageConfigService appPageConfigService;

	/**
	 *应用菜单配置
	 * @param model
	 * @return
	 */
	@RequiresPermissions(PERMISSIONPRE+"view")
	@RequestMapping(value = "/list")
	public String list(Model model,Integer appId) {
		OperatorCredential credential = getCredential();
		model.addAttribute("appLists", JSON.toJSONString(credential.getAppList()));
		model.addAttribute("appId",appId);
		return ADDRESSPRE+"appPageConfigList";
	}

	/**
	 * ajax查询
	 * @param appPageConfigCriteria
	 * @return
	 * @throws TqException
	 */
	@RequiresPermissions(PERMISSIONPRE+"view")
	@ResponseBody
	@RequestMapping(value = "/appPageConfigList")
	public String appPageConfigList(AppPageConfigCriteria appPageConfigCriteria) throws TqException{

		OperatorCredential credential = getCredential();
		try {
			if( credential == null){
                throw new TqException("登录过期，请重新登录");
			}
			if (appPageConfigCriteria.getPagesize() == 0) {
				appPageConfigCriteria.setPagesize(GlobalConfig.getPageSize());
			}
			//验证数据权限
			if( appPageConfigCriteria.getAppId() !=null){
				validAuthData(null,appPageConfigCriteria.getAppId());
			}
			ListVo<AppPageConfig> list = appPageConfigService.queryAppPageConfigList(appPageConfigCriteria);
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("list",list);
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

	/**
	 *
	 * @param appPageConfig
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
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

	/**
	 *
	 * @param appPageConfig
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
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

	/**
	 *
	 * @param id
	 * @param appId
	 * @param isEnable
	 * @param redirectAttributes
	 * @return
	 */
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

	/**
	 *
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
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

