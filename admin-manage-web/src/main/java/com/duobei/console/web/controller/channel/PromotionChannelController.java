package com.duobei.console.web.controller.channel;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelCriteria;
import com.duobei.core.operation.channel.service.PromotionChannelService;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.duobei.console.web.controller.base.BaseController;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/5
 */

@Controller
@RequestMapping(value = "${authzPath}/channel/promotion")
public class PromotionChannelController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(
            PromotionChannelController.class);

    private final static String PERMISSIONPRE = "channel:promotion:";
    private final static String ADDRESSPRE = "channel/promotion/";
    private final static String DESC = "推广渠道";

    @Resource
    private PromotionChannelService channelService;

  /*  @Autowired
    private ChannelStyleService channelStyleService;*/


    @RequiresPermissions(PERMISSIONPRE + "view")
    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("channelType", JSON.toJSONString(DictUtil.getDictList(ZD.channelType)));
        return ADDRESSPRE + "promotionChannelList";
    }

    @RequiresPermissions(PERMISSIONPRE + "view")
    @ResponseBody
    @RequestMapping(value = "/promotionChannelList")
    public String promotionChannelList(PromotionChannelCriteria promotionChannelCriteria) {
        OperatorCredential credential = getCredential();
        if (promotionChannelCriteria.getPagesize() == 0) {
            promotionChannelCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
         /*   ListVo<PromotionChannelVo> list = channelService
                    .queryPromotionChannelList(credential, promotionChannelCriteria);
            List<ChannelStyle> channelStyleList = channelStyleService.getAll();*/

            Map<String, Object> result = new HashMap<>();
            /*result.put("list", list);
            result.put("channelStyleList", channelStyleList);*/
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

  /*  @RequiresPermissions(PERMISSIONPRE + "view")
    @RequestMapping(value = "/form")
    public String form(@ModelAttribute("promotionChannel") PromotionChannel promotionChannel,
                       Model model, RedirectAttributes redirectAttributes) {
        PromotionChannelVo promotionChannelVo = new PromotionChannelVo();
        try {
            if (promotionChannel.getId() != null) {
                promotionChannel = channelService.queryPromotionChannelById(promotionChannel.getId());
            }
            List<ChannelStyle> channelStyleList = channelStyleService.getAll();
            BeanUtils.copyProperties(promotionChannel, promotionChannelVo);
            promotionChannelVo.setChannelStyleList(channelStyleList);
        } catch (Exception e) {
            if (e instanceof TqException) {
                addFaildMessage(redirectAttributes, e.getMessage());
            } else {
                log.warn("查询" + DESC + "异常", e);
                addFaildMessage(redirectAttributes, "查询" + DESC + "异常，请查看错误日志");
            }
            return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
        }
        model.addAttribute("promotionChannelVo", promotionChannelVo);
        return ADDRESSPRE + "promotionChannelForm";
    }


    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @RequestMapping(value = "/save")
    public String save(PromotionChannel promotionChannel, Model model,
                       RedirectAttributes redirectAttributes) {
        OperatorCredential credential = getCredential();
        try {
            if (promotionChannel.getId() == null) {
                channelService.addPromotionChannel(credential, promotionChannel);
            } else {
                channelService.updatePromotionChannel(credential, promotionChannel);
            }
        } catch (Exception e) {
            if (e instanceof TqException) {
                addFaildMessage(model, e.getMessage());
            } else {
                log.warn("save" + DESC + "异常", e);
                addFaildMessage(model, "请查看错误日志");
            }
            return form(promotionChannel, model,redirectAttributes);
        }
        addMessage(redirectAttributes, "保存" + DESC + "成功!");
        return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
    }

    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @RequestMapping(value = "/delete")
    public String delete(Integer id, RedirectAttributes redirectAttributes) {
        OperatorCredential credential = getCredential();
        try {
            channelService.deletePromotionChannel(credential, id);
        } catch (Exception e) {
            if (e instanceof TqException) {
                addFaildMessage(redirectAttributes, e.getMessage());
            } else {
                log.warn("delete" + DESC + "异常", e);
                addFaildMessage(redirectAttributes, "删除" + DESC + "异常，请查看错误日志");
            }
            return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
        }
        addMessage(redirectAttributes, "删除" + DESC + "成功");
        return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
    }
*/
}