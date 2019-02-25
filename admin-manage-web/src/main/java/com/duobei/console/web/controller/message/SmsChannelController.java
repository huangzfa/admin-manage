package com.duobei.console.web.controller.message;

import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.domain.SmsChannel;
import com.duobei.core.message.domain.criteria.SmsChannelCriteria;
import com.duobei.core.message.service.SmsChannelService;
import com.duobei.core.message.vo.SmsStyleVo;
import com.duobei.exception.TqException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

@Controller
@RequestMapping(value = "${authzPath}/ms/smsc")
public class SmsChannelController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(SmsChannelController.class);

	private final static String PERMISSIONPRE = "ms:smsc:";
	private final static String ADDRESSPRE = "ms/smsc/";
	private final static String DESC = "短信渠道";
	@Autowired
	private SmsChannelService smsChannelService;

	@RequiresPermissions(PERMISSIONPRE+"view")
	@RequestMapping(value = "/list")
	public String list() {
		return ADDRESSPRE+"smsChannelList";
	}

	@RequiresPermissions(PERMISSIONPRE+"view")
	@ResponseBody
	@RequestMapping(value = "/smsChannelList")
	public String smsChannelList(SmsChannelCriteria smsChannelCriteria) {

		OperatorCredential credential = getCredential();
		if (smsChannelCriteria.getPagesize() == 0) {
			smsChannelCriteria.setPagesize(GlobalConfig.getPageSize());
		}
		try {
			ListVo<SmsChannel> list = smsChannelService.querySmsChannelList(credential, smsChannelCriteria);
			List<SmsStyleVo> smsStyleVoList = smsChannelService.getSmsStyleVo();
			Map<String, Object> result = new HashMap<>();
			result.put("list", list);
			result.put("smsStyleVoList", smsStyleVoList);
			return successJsonResult(result, "success");
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
	public String form(@ModelAttribute("smsChannel") SmsChannel smsChannel, Model model,RedirectAttributes redirectAttributes){
		try {
			if (smsChannel.getId() != null) {
				smsChannel = smsChannelService.querySmsChannelById(smsChannel.getId());
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
		model.addAttribute("smsChannel", smsChannel);
		return ADDRESSPRE+"smsChannelForm";
		}

	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	@RequestMapping(value = "/save")
	public String save(SmsChannel smsChannel, Model model, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			if (smsChannel.getId() == null) {
				smsChannelService.addSmsChannel(credential, smsChannel);
			} else {
				smsChannelService.updateSmsChannel(credential, smsChannel);
			}
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(model, e.getMessage());
			} else {
				log.warn("save"+DESC+"异常", e);
				addFaildMessage(model, "请查看错误日志");
			}
			return form(smsChannel, model,redirectAttributes);
		}
		addMessage(redirectAttributes, "保存"+DESC+"成功!");
		return "redirect:" + this.authzPath + "/" +ADDRESSPRE+"list";
	}

	@RequiresPermissions({ PERMISSIONPRE+"edit" })
	@RequestMapping(value = "/delete")
	public String delete(Integer id, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			smsChannelService.deleteSmsChannel(credential, id);
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

	@RequiresPermissions({PERMISSIONPRE + "edit"})
	@RequestMapping(value = "/changeState")
	public String changeState(Integer id, Integer status, RedirectAttributes redirectAttributes) {
		OperatorCredential credential = getCredential();
		try {
			smsChannelService.changeState(credential, id, status);
		} catch (Exception e) {
			if (e instanceof TqException) {
				addFaildMessage(redirectAttributes, e.getMessage());
			} else {
				addFaildMessage(redirectAttributes, "改变" + DESC + "状态异常，请查看错误日志");
			}
			return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
		}
		addMessage(redirectAttributes, "改变" + DESC + "状态成功");
		return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
	}

}
