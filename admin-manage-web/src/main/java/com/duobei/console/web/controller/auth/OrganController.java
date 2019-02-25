package main.java.com.duobei.console.web.controller.auth;

import java.util.ArrayList;
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
import com.duobei.core.auth.domain.Organ;
import com.duobei.core.auth.domain.OrganType;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.OrganCriteria;
import com.duobei.core.auth.domain.vo.OrganVo;
import com.duobei.core.auth.service.OrganService;
import com.duobei.exception.TqException;

@Controller
@RequestMapping(value = "${authzPath}/sys/organ")
public class OrganController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(OrganController.class);
	@Autowired
	private OrganService organService;
	
	@RequiresPermissions("sys:organ:view")
	@RequestMapping(value = "/index")
	public String index(){
	    return "sys/org/organ/organIndex";
	}
	
	@RequiresPermissions("sys:organ:view")
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
	
	@RequiresPermissions("sys:organ:view")
	@RequestMapping(value = "/list")
	public String list(Integer selectOrganId,Model model){
		if (selectOrganId==null) {
			selectOrganId=getCredential().getOrganId();
		}
		model.addAttribute("selectOrganId", selectOrganId);
		return "sys/org/organ/organList";
	}
	
	@RequiresPermissions("sys:organ:view")
	@ResponseBody
	@RequestMapping(value = "/organList")
	public String organList(OrganCriteria criteria) {
		OperatorCredential credential=getCredential();
		if (criteria.getSelectOrganId()==null) {
			criteria.setSelectOrganId(credential.getOrganId());
		}
		if (criteria.getPagesize()==0) {
			criteria.setPagesize(GlobalConfig.getPageSize());
		}
		try {
			ListVo<OrganVo> list = organService.queryOrganListByParent(criteria);
			return successJsonResult("success", "list", list);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("查询组织信息异常",e);
				return failJsonResult("查询组织信息异常，请查看错误日志");
			}
		}
	}
	
	@RequiresPermissions("sys:organ:view")
	@RequestMapping(value = "/form")
	public String form(@ModelAttribute("organ")OrganVo organ,Model model, RedirectAttributes redirectAttributes){
		Integer selectOrganId=organ.getSelectOrganId();
		Integer parentOrganId=selectOrganId;
		List<OrganType> organTypes=new ArrayList<OrganType>();
		try {
			if (selectOrganId==null) {
				throw new TqException("请选择组织");
			}
			if (organ.getOrganId()!=null) {
				organ=organService.queryOrganVoById(organ.getOrganId());
				parentOrganId=organ.getParentOrganId();
				organ.setSelectOrganId(selectOrganId);
			}else{
				organ.setParentOrganId(selectOrganId);
			}
			organ.setParent(organService.queryOrganById(parentOrganId));
			//获取符合组织架构规则的可添加的组织类型,
			//如果是添加不包含本组织类型，
			//如果是修改不能修改组织类型，只包含本组织类型
			if (organ.getOrganId()==null) {
				List<OrganType> list=organService.queryCanAddChildOrganTypeForOrganRule(organ.getParentOrganId());
				if (list!=null) {
					organTypes.addAll(list);
				}
			}else{
				organTypes.add(organService.queryOrganTypeById(organ.getOrganTypeId()));
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("查询组织信息异常",e);
				addFaildMessage(redirectAttributes, "查询组织信息异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/organ/list?selectOrganId="+selectOrganId.intValue();
		}
		model.addAttribute("organ", organ);
		model.addAttribute("organTypes", organTypes);
	    return "sys/org/organ/organForm";
	}
	@RequiresPermissions({"sys:organ:edit"})
	@RequestMapping(value = "/save")
	public String save(OrganVo organ, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		Integer selectOrganId=organ.getSelectOrganId();
		try {
			if (organ.getOrganId()==null) {
				organService.addOrgan(credential,organ);
			}else{
				organService.updateOrgan(credential,organ);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			}else{
				log.warn("save组织异常",e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(organ, model,redirectAttributes);
		}
		addMessage(redirectAttributes, "保存组织【" + organ.getOrganName() + "】成功!");
		return "redirect:" + this.authzPath + "/sys/organ/list?selectOrganId="+selectOrganId.intValue();
	}
	
	@RequiresPermissions({"sys:organ:edit"})
	@RequestMapping(value = "/delete")
	public String delete(Integer selectOrganId,Integer organId, RedirectAttributes redirectAttributes) {
		OperatorCredential credential=getCredential();
		try {
			organService.deleteOrgan(credential,organId);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			}else{
				log.warn("delete组织异常",e);
				addFaildMessage(redirectAttributes, "删除组织异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/sys/organ/list?selectOrganId="+selectOrganId.intValue();
		}
		addMessage(redirectAttributes, "删除组织成功");
		return "redirect:" + this.authzPath + "/sys/organ/list?selectOrganId="+selectOrganId.intValue();
	}
	
	@RequiresPermissions("sys:organ:view")
	@ResponseBody
	@RequestMapping(value = "/checkByOrganRule")
	public String checkByOrganRule(Integer selectOrganId) {
		try {
			boolean flag=organService.isCanAddChildOrganTypeForOrganRule(selectOrganId);
			return successJsonResult("success", "flag", flag);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("校验是否可以添加下级组织异常",e);
				return failJsonResult("校验异常，请查看错误日志");
			}
		}
	}
	
	
	@RequiresPermissions("sys:organ:edit")
	@ResponseBody
	@RequestMapping(value = "/checkOrganCode")
	public String checkOrganCode(Integer organId,String organCode) {
		try {
			Organ organ=organService.queryOrganByCode(organCode);
			if (organ==null) {
				return "true";
			}
			if (organId==null) {
				return "false";
			}
			if(organ.getOrganId().equals(organId)){
				return "true";
			}
			return "false";
		} catch (Exception e) {
			log.warn("校验是否已经存在组织编码",e);
			return "true";
		}
	}
}
