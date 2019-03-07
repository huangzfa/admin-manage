
package com.duobei.console.web.controller.app;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.encrypt.MD5Util;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.operation.app.domain.criteria.AppPageConfigCriteria;
import com.duobei.core.operation.app.service.AppPageConfigService;
import com.duobei.core.operation.app.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @author litianxiong
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

	@Resource
	private AppPageConfigService appPageConfigService;
	@Resource
	private AppService appService;

	/**
	 *应用菜单配置
	 * @param model
	 * @return
	 */
	@RequiresPermissions(PERMISSIONPRE+"view")
	@RequestMapping(value = "/list")
	public String list(Model model,Integer appId) {
		OperatorCredential credential = getCredential();
		/**
		 * app下拉框
		 */
		model.addAttribute("appLists", JSON.toJSONString(credential.getAppList()));
		/**
		 * appId条件
		 */
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
			//登录判断
			if( credential == null){
                throw new TqException("登录过期，请重新登录");
			}
			//验证数据权限
			if( appPageConfigCriteria.getAppId() !=null){
				validAuthData(null,appPageConfigCriteria.getAppId());
			}else{
				throw  new TqException("应用数据查询失败");
			}
			//页数赋值
			if (appPageConfigCriteria.getPagesize() == 0) {
				appPageConfigCriteria.setPagesize(GlobalConfig.getPageSize());
			}

			//获取数据列表
			ListVo<AppPageConfig> list = appPageConfigService.getListByQuery(appPageConfigCriteria);
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
			//验证数据权限
			if( appPageConfig.getAppId() !=null){
				validAuthData(null,appPageConfig.getAppId());
			}else{
				throw  new TqException("应用数据查询失败");
			}
			//如果存在id 则是修改操作，查询相关信息
			if (appPageConfig.getId() != null) {
                appPageConfig = appPageConfigService.getById(appPageConfig.getId());
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
			//验证数据权限
			if( appPageConfig.getAppId() !=null){
				validAuthData(null,appPageConfig.getAppId());
			}else{
				throw  new TqException("应用数据查询失败");
			}

			appPageConfig.setModifyOperatorId(credential.getOpId());
			appPageConfig.setModifyTime(new Date());
			if (appPageConfig.getId() == null) {
				/**
				 *  根据应用id查询产品信息
				 */
				App app = appService.getAppById(appPageConfig.getAppId());
				appPageConfig.setProductId(app.getProductId());
				/**
				 * 新增配置： 默认不启用 ，人审/未人审和原生H5 原型图暂未提供该功能，询问产品后，默认为为未人审和原生
				 */
				appPageConfig.setIsExamine(0);
				appPageConfig.setIsEnable(0);
				appPageConfig.setMenuType(2);
				/**
				 *  随机生成菜单编码 MD5（应用id+菜单名称）
				 */
				appPageConfig.setMenuCode(MD5Util.encrypt(appPageConfig.getAppId()+appPageConfig.getMenuName()));
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
	 * 启用/禁用
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
			//验证数据权限
			if( appId !=null){
				validAuthData(null,appId);
			}else{
				throw new TqException("应用数据查询失败");
			}
			AppPageConfig oldConfig = appPageConfigService.getById(id);
			if (oldConfig == null){
				throw new TqException("应用配置不存在");
			}
			//获取实体类信息
			AppPageConfig appPageConfig = new AppPageConfig();
			appPageConfig.setId(id);
			appPageConfig.setModifyTime(new Date());
			appPageConfig.setModifyOperatorId(credential.getOpId());
			appPageConfig.setIsEnable(isEnable);
			//启用/禁用
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
	 * 删除（禁用）
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	//@RequestMapping(value = "/delete")
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

