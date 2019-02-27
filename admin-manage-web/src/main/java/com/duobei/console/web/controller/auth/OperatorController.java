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

import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.manage.auth.domain.OperatorRoleKey;
import com.duobei.core.manage.auth.domain.Organ;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.auth.domain.criteria.RoleCriteria;
import com.duobei.core.manage.auth.domain.criteria.UserCriteria;
import com.duobei.core.manage.auth.domain.vo.OperatorVo;
import com.duobei.core.manage.auth.domain.vo.RoleVo;
import com.duobei.core.manage.auth.service.OperatorService;
import com.duobei.core.manage.auth.service.OrganService;
import com.duobei.core.manage.auth.service.RoleService;
import com.duobei.dic.ZD;
import com.duobei.common.exception.TqException;

@Controller
@RequestMapping(value = "${authzPath}/sys/user")
public class OperatorController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(OperatorController.class);
	@Autowired
	private OrganService organService;
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private RoleService roleService;
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/index")
	public String index(){
	    return "sys/user/userIndex";
	}
	
	@RequiresPermissions("sys:user:view")
	@ResponseBody
	@RequestMapping(value = "/treeOrgans")
	public String treeOrgans(HttpServletRequest request) {
		try {
			List<Organ> list = organService.queryAllOrgan();
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("查询组织架构异常",e);
				return failJsonResult("查询组织架构异常，请查看错误日志");
			}
		}
	}
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/list")
	public String list(Integer selectOrganId,Model model){
		if (selectOrganId==null) {
			selectOrganId=getCredential().getOrganId();
		}
		model.addAttribute("selectOrganId", selectOrganId);
		model.addAttribute("aLoginName", GlobalConfig.getSuperAdmin());
		return "sys/user/userList";
	}
	
	@RequiresPermissions("sys:user:view")
	@ResponseBody
	@RequestMapping(value = "/userList")
	public String userList(UserCriteria userCriteria) {
		OperatorCredential credential=getCredential();
		if (userCriteria.getSelectOrganId()==null) {
			userCriteria.setSelectOrganId(credential.getOrganId());
		}
		if (userCriteria.getPagesize()==0) {
			userCriteria.setPagesize(GlobalConfig.getPageSize());
		}
		try {
			ListVo<OperatorVo> list = operatorService.queryOperatorList(userCriteria);
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("查询用户列表异常",e);
				return failJsonResult("查询用户列表异常，请查看错误日志");
			}
		}
	}
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute("operator")OperatorVo operator,Model model, RedirectAttributes redirectAttributes){
		Integer selectOrganId=operator.getSelectOrganId();
		Integer queryOrganId=selectOrganId;
		try {
			if (selectOrganId==null) {
				throw new TqException("请选择组织");
			}
			if (operator.getOpId()!=null) {
				operator=operatorService.queryOperatorVoById(operator.getOpId());
				queryOrganId=operator.getOrganId();
				operator.setSelectOrganId(selectOrganId);
			}else{
				operator.setOperatorState(ZD.state_open);
				operator.setOrganId(selectOrganId);
			}
			Organ organ=organService.queryOrganById(queryOrganId);
			operator.setOrganName(organ.getOrganName());
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("查询用户信息异常",e);
				addFaildMessage(redirectAttributes, "查询用户信息异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/user/list?selectOrganId="+selectOrganId.intValue();
		}
		model.addAttribute("operator", operator);
	    return "sys/user/userForm";
	}
	@RequiresPermissions({"sys:user:edit"})
	@RequestMapping(value = "/save")
	public String save(OperatorVo operator, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		Integer selectOrganId=operator.getSelectOrganId();
		Operator editOperator=operator;
		String pwd=operator.getLoginPwd();
		String msg = "保存用户【" + operator.getLoginName() + "】成功!";
		try {
			if (operator.getOpId()==null) {
				operatorService.addOperator(credential,editOperator);
			}else{
				operatorService.updateOperator(credential,editOperator);
			}
		} catch (Exception e) {
			if (operator.getOpId()==null) {
				operator.setLoginPwd(pwd);
			}
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			}else{
				log.warn("save用户异常",e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(operator, model,redirectAttributes);
		}
		addMessage(redirectAttributes, msg );
		return "redirect:" + this.authzPath + "/sys/user/list?selectOrganId="+selectOrganId.intValue();
	}
	
	@RequiresPermissions({"sys:user:edit"})
	@RequestMapping(value = "/delete")
	public String delete(Integer selectOrganId,Integer opId, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		try {
			operatorService.deleteOperator(credential,opId);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("delete用户异常",e);
				addFaildMessage(redirectAttributes, "删除用户异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/user/list?selectOrganId="+selectOrganId.intValue();
		}
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:" + this.authzPath + "/sys/user/list?selectOrganId="+selectOrganId.intValue();
	}
	
	@RequiresPermissions("sys:user:edit")
	@ResponseBody
	@RequestMapping(value = "/checkLoginName")
	public String checkLoginName(Integer opId,String loginName) {
		try {
			Operator operator=operatorService.queryOperatorByLoginName(loginName);
			if (operator==null || opId == null) {
				return "true";
			}
			if (opId==null) {
				return "true";
			}
			if(operator.getOpId().equals(opId)){
				return "true";
			}
			return "false";
		} catch (Exception e) {
			log.warn("校验是否已经存登录账号异常",e);
			return "true";
		}
	}
	//跳转到分配角色页面
	@RequiresPermissions("sys:user:assignRoles")
	@RequestMapping(value = "/toAssignRoles")
	public String toAssignRoles(Integer selectOrganId,Integer opId,Model model){
		OperatorVo operator=null;
		try {
			operator = operatorService.queryOperatorVoById(opId);
		} catch (TqException e) {
		}
		model.addAttribute("selectOrganId", selectOrganId);
		model.addAttribute("opId", opId);
		model.addAttribute("operator", operator);
		return "sys/user/assignRoles";
	}
	//查询可以分配的角色
	@RequiresPermissions("sys:user:assignRoles")
	@ResponseBody
	@RequestMapping(value = "/queryAssignRoles")
	public String queryAssignRoles(Integer opId,String roleName) {
		OperatorCredential credential=getCredential();
		RoleCriteria roleCriteria=new RoleCriteria();
		roleCriteria.setRoleStateZd(ZD.state_open);
		roleCriteria.setRoleName(roleName);
		roleCriteria.setPagesize(0);
		try {
			//获取角色列表
			ListVo<RoleVo> list=roleService.queryRoleList(credential, roleCriteria);
			//获取用户已拥有的角色
			List<OperatorRoleKey> srList=operatorService.queryOperatorRoleIds(opId);
			
			if(list.getRows()!=null&&list.getRows().size()>0&&srList!=null&&srList.size()>0){
				for (RoleVo rv:list.getRows()) {
					Integer roleId=rv.getRoleId();
					rv.setChecked(false);
					for (OperatorRoleKey sr:srList) {
						if (roleId.equals(sr.getRoleId())) {
							rv.setChecked(true);
							break;
						}
					}
				}
			}
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("分配角色，查询角色列表异常",e);
				return failJsonResult("查询角色列表异常，请查看错误日志");
			}
		}
	}
	
	@RequiresPermissions("sys:user:assignRoles")
	@ResponseBody
	@RequestMapping(value = "/assignRoles")
	public String assignRoles(Integer opId,String selectRoleIds){
		log.info("分配角色[opId="+opId+"][selectRoleIds="+selectRoleIds+"]");
		try {
			operatorService.assignRoles(opId,selectRoleIds);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("分配角色异常",e);
				return failJsonResult("分配角色异常，请查看错误日志");
			}
		}
		return simpleSuccessJsonResult("角色分配成功!");
	}
	
	
	@RequestMapping(value = "/userInfo")
	public String userInfo(Model model){
		OperatorCredential credential=getCredential();
		OperatorVo operator=null;
		try {
			operator = operatorService.queryOperatorVoById(credential.getOpId());
		} catch (TqException e) {
		}
		model.addAttribute("operator", operator);
		return "sys/userInfo/userInfo";
	}
	
	@RequiresPermissions("sys:user:modifyPwd")
	@RequestMapping(value = "/toModifyPwd")
	public String toModifyPwd(Model model){
		OperatorCredential credential=getCredential();
		model.addAttribute("credential", credential);
		return "sys/userInfo/modifyPwd";
	}
	
	@RequiresPermissions("sys:user:modifyPwd")
	@RequestMapping(value = "/modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model){
		OperatorCredential credential=getCredential();
		try {
			operatorService.modifyLoginPwd(credential.getOpId(),oldPassword,newPassword);
			addMessage(model, "修改成功!");
			
			return "redirect:/logout";//调用LoginController的登出action
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			}else{
				log.warn("修改密码异常",e);
				addFaildMessage(model, "修改密码异常，请查看错误日志");
			}
		}
		model.addAttribute("credential", credential);
		return "sys/userInfo/modifyPwd";
	}
}
