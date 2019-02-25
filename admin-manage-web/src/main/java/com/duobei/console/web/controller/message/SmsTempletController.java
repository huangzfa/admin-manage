package com.duobei.console.web.controller.message;

import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.domain.SmsTemplet;
import com.duobei.core.message.domain.criteria.SmsTempletCriteria;
import com.duobei.core.message.service.SmsChannelService;
import com.duobei.core.message.service.SmsTempletService;
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
@RequestMapping(value = "${authzPath}/ms/smst")
public class SmsTempletController extends BaseController {

  private final static Logger log = LoggerFactory.getLogger(
      SmsTempletController.class);
  private final static String PERMISSIONPRE = "ms:smst:";
  private final static String ADDRESSPRE = "ms/smst/";
  private final static String DESC = "短信模板";
  @Autowired
  private SmsTempletService smsTempletService;

  @Autowired
  private SmsChannelService smsChannelService;

  @RequiresPermissions(PERMISSIONPRE + "view")
  @RequestMapping(value = "/list")
  public String list() {
    return ADDRESSPRE + "smsTempletList";
  }

  @RequiresPermissions(PERMISSIONPRE + "view")
  @ResponseBody
  @RequestMapping(value = "/smsTempletList")
  public String smsTempletList(SmsTempletCriteria smsTempletCriteria) {
    OperatorCredential credential = getCredential();
    if (smsTempletCriteria.getPagesize() == 0) {
      smsTempletCriteria.setPagesize(GlobalConfig.getPageSize());
    }
    try {
      ListVo<SmsTemplet> list = smsTempletService.querySmsTempletList(credential, smsTempletCriteria);
      List<SmsStyleVo> smsStyleVoList = smsChannelService.getSmsStyleVo();
      Map<String, Object> result = new HashMap<>();
      result.put("list", list);
      result.put("smsStyleVoList", smsStyleVoList);
      return successJsonResult(result, "success");
    } catch (Exception e) {
      if (e instanceof TqException) {
        return failJsonResult(e.getMessage());
      } else {
        log.warn("查询" + DESC + "列表异常", e);
        return failJsonResult("查询" + DESC + "列表异常，请查看错误日志");
      }
    }
  }

  @RequiresPermissions(PERMISSIONPRE + "view")
  @RequestMapping(value = "/form")
  public String form(@ModelAttribute("smsTemplet") SmsTemplet smsTemplet, Model model,
      RedirectAttributes redirectAttributes) {
    try {
      if (smsTemplet.getId() != null) {
        smsTemplet = smsTempletService.querySmsTempletById(smsTemplet.getId());
      }
    } catch (Exception e) {
      if (e instanceof TqException) {
        addFaildMessage(redirectAttributes, e.getMessage());
      } else {
        log.warn("查询" + DESC + "异常", e);
        addFaildMessage(redirectAttributes, "查询" + DESC + "异常，请查看错误日志");
      }
      return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
    }
    model.addAttribute("smsTemplet", smsTemplet);
    return ADDRESSPRE + "smsTempletForm";
  }

  @RequiresPermissions({PERMISSIONPRE + "edit"})
  @RequestMapping(value = "/save")
  public String save(SmsTemplet smsTemplet, Model model, RedirectAttributes redirectAttributes)
      throws TqException {
    OperatorCredential credential = getCredential();
    try {
      if (smsTemplet.getId() == null) {
        smsTempletService.addSmsTemplet(credential, smsTemplet);
      } else {
        smsTempletService.updateSmsTemplet(credential, smsTemplet);
      }
    } catch (Exception e) {
      if (e instanceof TqException) {
        addFaildMessage(model, e.getMessage());
      } else {
        log.warn("save" + DESC + "异常", e);
        addFaildMessage(model, "请查看错误日志");
      }
      return form(smsTemplet, model,redirectAttributes);
    }
    addMessage(redirectAttributes, "保存" + DESC + "成功!");
    return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
  }

  @RequiresPermissions({ PERMISSIONPRE+"edit" })
  @RequestMapping(value = "/delete")
  public String delete(Integer id, RedirectAttributes redirectAttributes) {
    OperatorCredential credential = getCredential();
    try {
      smsTempletService.deleteSmsTemplet(credential, id);
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
      smsTempletService.changeState(credential, id, status);
    } catch (Exception e) {
      if (e instanceof TqException) {
        addFaildMessage(redirectAttributes, e.getMessage());
      } else {
        log.error(e.getMessage());
        addFaildMessage(redirectAttributes, "改变" + DESC + "状态异常，请查看错误日志");
      }
      return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
    }
    addMessage(redirectAttributes, "改变" + DESC + "状态成功");
    return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
  }
}
