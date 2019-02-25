package com.duobei.console.web.controller.message;

import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.domain.JpushTemplet;
import com.duobei.core.message.domain.criteria.JpushTempletCriteria;
import com.duobei.core.message.service.JpushTempletService;
import com.duobei.exception.TqException;
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
@RequestMapping(value = "${authzPath}/ms/jt")
public class JpushTempletController extends BaseController {

  private final static Logger log = LoggerFactory.getLogger(
      JpushTempletController.class);
  private final static String PERMISSIONPRE = "ms:jt:";
  private final static String ADDRESSPRE = "ms/jt/";
  private final static String DESC = "推送模板";
  @Autowired
  private JpushTempletService jpushTempletService;

  @RequiresPermissions(PERMISSIONPRE + "view")
  @RequestMapping(value = "/list")
  public String list() {
    return ADDRESSPRE + "jpushTempletList";
  }

  @RequiresPermissions(PERMISSIONPRE + "view")
  @ResponseBody
  @RequestMapping(value = "/jpushTempletList")
  public String jpushTempletList(JpushTempletCriteria jpushTempletCriteria) {
    OperatorCredential credential = getCredential();
    if (jpushTempletCriteria.getPagesize() == 0) {
      jpushTempletCriteria.setPagesize(GlobalConfig.getPageSize());
    }
    try {
      ListVo<JpushTemplet> list = jpushTempletService.queryJpushTempletList(credential, jpushTempletCriteria);
      return successJsonResult("success", "list", list);
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
  public String form(@ModelAttribute("jpushTemplet") JpushTemplet jpushTemplet, Model model,
      RedirectAttributes redirectAttributes) {
    try {
      if (jpushTemplet.getId() != null) {
        jpushTemplet = jpushTempletService.queryJpushTempletById(jpushTemplet.getId());
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
    model.addAttribute("jpushTemplet", jpushTemplet);
    return ADDRESSPRE + "jpushTempletForm";
  }

  @RequiresPermissions({PERMISSIONPRE + "edit"})
  @RequestMapping(value = "/save")
  public String save(JpushTemplet jpushTemplet, Model model, RedirectAttributes redirectAttributes)
      throws TqException {
    OperatorCredential credential = getCredential();
    try {
      if (jpushTemplet.getId() == null) {
        jpushTempletService.addJpushTemplet(credential, jpushTemplet);
      } else {
        jpushTempletService.updateJpushTemplet(credential, jpushTemplet);
      }
    } catch (Exception e) {
      if (e instanceof TqException) {
        addFaildMessage(model, e.getMessage());
      } else {
        log.warn("save" + DESC + "异常", e);
        addFaildMessage(model, "请查看错误日志");
      }
      return form(jpushTemplet, model,redirectAttributes);
    }
    addMessage(redirectAttributes, "保存" + DESC + "成功!");
    return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
  }


  @RequiresPermissions({ PERMISSIONPRE+"edit" })
  @RequestMapping(value = "/delete")
  public String delete(Integer id, RedirectAttributes redirectAttributes) {
    OperatorCredential credential = getCredential();
    try {
      jpushTempletService.deleteJpushTemplet(credential, id);
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
      jpushTempletService.changeState(credential, id, status);
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
