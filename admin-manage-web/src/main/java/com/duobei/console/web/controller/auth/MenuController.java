package com.duobei.console.web.controller.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
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
import com.duobei.core.auth.domain.Menu;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.helper.MenuHelper;
import com.duobei.core.auth.service.MenuService;
import com.duobei.dic.ZD;
import com.duobei.common.exception.TqException;

@Controller
@RequestMapping(value = "${authzPath}/sys/menu")
public class MenuController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;

	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	// binder.registerCustomEditor(Long.class, "price", new
	// StringPriceToLong());
	// }

	// @ModelAttribute("menu")
	// public Menu get(@RequestParam(required=false) Integer menuId){
	// if (menuId!=null) {
	// return menuService.queryMenuByMenuId(menuId);
	// }
	// return new Menu();
	// }

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "/index")
	public String menuIndex() {
		return "sys/menu/menuIndex";
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "/list")
	public String menuList() {
		return "sys/menu/menuList";
	}

	@RequiresPermissions("sys:menu:view")
	@ResponseBody
	@RequestMapping(value = "/allMenus")
	public String allMenus(HttpServletRequest request) {
		String menuType = WebUtils.getCleanParam(request, "menuType");
		if (StringUtils.isBlank(menuType)) {
			menuType = null;
		} else {
			if (!StringUtils.equals(ZD.menuType_m, menuType) && !StringUtils.equals(ZD.menuType_mo, menuType)) {
				return failJsonResult("无效的菜单类型");
			}
		}
		List<Menu> menus = menuService.queryAllMenu(menuType);
		return successJsonResult("success", "menus", menus);
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute("menu") Menu menu, Model model) {
		if (menu.getMenuId() != null) {
			menu = menuService.queryMenuByMenuId(menu.getMenuId());
		} else {
			menu.setState(ZD.state_open);
		}
		if (menu.getParentId() == null) {
			menu.setParentId(MenuHelper.rootMenuId);
		}
		menu.setParent(menuService.queryMenuByMenuId(menu.getParentId()));
		model.addAttribute("menu", menu);
		return "sys/menu/menuForm";
	}

	@RequiresPermissions({ "sys:menu:edit" })
	@RequestMapping(value = "/save")
	public String save(Menu menu, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			if (menu.getMenuId() == null) {
				menuService.addMenu(credential, menu);
			} else {
				menuService.updateMenu(credential, menu);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			} else {
				log.warn("save菜单异常", e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(menu, model);
		}
		addMessage(redirectAttributes, "保存菜单【" + menu.getMenuName() + "】成功!");
		return "redirect:" + this.authzPath + "/sys/menu/list";
	}

	@RequiresPermissions({ "sys:user:edit" })
	@RequestMapping(value = "/delete")
	public String delete(Integer menuId, RedirectAttributes redirectAttributes) {
		try {
			menuService.deleteMenu(menuId);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				log.warn("delete菜单异常", e);
				addFaildMessage(redirectAttributes, "删除菜单异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/menu/list";
		}
		addMessage(redirectAttributes, "删除菜单成功");
		return "redirect:" + this.authzPath + "/sys/menu/list";
	}

	@RequiresPermissions("sys:menu:view")
	@ResponseBody
	@RequestMapping(value = "/treeData")
	public String treeData(HttpServletRequest request) {
		// String menuType = WebUtils.getCleanParam(request, "menuType");
		List<Menu> menus = menuService.queryAllMenu(ZD.menuType_m);
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		if (menus != null && menus.size() > 0) {
			for (Menu m : menus) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", m.getMenuId());
				map.put("pId", m.getParentId());
				map.put("name", m.getMenuName());
				menuList.add(map);
			}
		}
		return successJsonResult("success", "menus", menuList);
	}
}
