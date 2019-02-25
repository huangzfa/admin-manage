package main.java.com.duobei.console.web.controller.auth;

import java.util.List;

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

import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.auth.domain.Menu;
import com.duobei.core.auth.domain.RoleMenuKey;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.RoleCriteria;
import com.duobei.core.auth.domain.vo.RoleVo;
import com.duobei.core.auth.service.MenuService;
import com.duobei.core.auth.service.RoleService;
import com.duobei.dic.ZD;
import com.duobei.exception.TqException;

@Controller
@RequestMapping(value = "${authzPath}/sys/role")
public class RoleController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/list")
	public String list() {
		return "sys/role/roleList";
	}

	@RequiresPermissions("sys:role:view")
	@ResponseBody
	@RequestMapping(value = "/roleList")
	public String roleList(RoleCriteria roleCriteria) {
		OperatorCredential credential = getCredential();
		if (roleCriteria.getPagesize() == 0) {
			roleCriteria.setPagesize(GlobalConfig.getPageSize());
		}
		try {
			ListVo<RoleVo> list = roleService.queryRoleList(credential, roleCriteria);
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			} else {
				log.warn("查询角色列表异常", e);
				return failJsonResult("查询角色列表异常，请查看错误日志");
			}
		}
	}

	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute("role") RoleVo role, Model model, RedirectAttributes redirectAttributes) {
		// OperatorCredential credential=getCredential();
		List<Menu> menuList = null;
		try {
			if (role.getRoleId() != null) {
				role = roleService.queryRoleById(role.getRoleId());
				List<RoleMenuKey> rms = roleService.queryRoleMenuByRoleId(role.getRoleId());
				if (rms != null && rms.size() > 0) {
					for (RoleMenuKey rm : rms) {
						role.getMenuIdList().add(rm.getMenuId());
					}
				}
			} else {
				role.setRoleState(ZD.state_open);
			}
			// menuList=menuService.queryAccreditMenu(credential);
			menuList = menuService.queryAllMenu(null);

		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				log.warn("查询角色信息异常", e);
				addFaildMessage(redirectAttributes, "查询角色信息异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/role/list";
		}
		model.addAttribute("role", role);
		model.addAttribute("menuList", menuList);
		return "sys/role/roleForm";
	}

	@RequiresPermissions({ "sys:role:edit" })
	@RequestMapping(value = "/save")
	public String save(RoleVo role, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			if (role.getRoleId() == null) {
				roleService.addRole(credential, role);
			} else {
				roleService.updateRole(credential, role);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			} else {
				log.warn("save角色异常", e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(role, model, redirectAttributes);
		}
		addMessage(redirectAttributes, "保存角色【" + role.getRoleName() + "】成功!");
		return "redirect:" + this.authzPath + "/sys/role/list";
	}

	@RequiresPermissions({ "sys:role:edit" })
	@RequestMapping(value = "/delete")
	public String delete(Integer roleId, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			roleService.deleteRole(credential, roleId);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				log.warn("delete角色异常", e);
				addFaildMessage(redirectAttributes, "删除角色异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/role/list";
		}
		addMessage(redirectAttributes, "删除角色成功");
		return "redirect:" + this.authzPath + "/sys/role/list";
	}
}
