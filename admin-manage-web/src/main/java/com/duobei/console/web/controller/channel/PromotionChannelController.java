package com.duobei.console.web.controller.channel;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.domain.Dict;
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
import java.util.Date;
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
    private PromotionChannelService promotionChannelService;

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
           ListVo<PromotionChannel> list = promotionChannelService.getPromotionListByQuery(promotionChannelCriteria);

            Map<String, Object> result = new HashMap<>();
            result.put("list", list);
            return successJsonResult(result, "success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.error("查询" + DESC + "列表异常", e);
                return failJsonResult("查询" + DESC + "列表异常，请查看错误日志");
            }
        }
    }

    @RequiresPermissions(PERMISSIONPRE + "view")
    @RequestMapping(value = "/form")
    public String form(@ModelAttribute("promotionChannel") PromotionChannel promotionChannel,
                       Model model, RedirectAttributes redirectAttributes) {
        try {
            if (promotionChannel.getId() != null) {
                promotionChannel = promotionChannelService.getById(promotionChannel.getId());
            }else{
                //新建默认 渠道类型：渠道  审核状态：审核完成 渠道类型：渠道
                promotionChannel.setChannelType(ZD.channelType_h5);
                promotionChannel.setApproveStatus(ZD.channelApproveType_finsh);
                promotionChannel.setChannelState(ZD.channelStatus_yes);
            }
            //渠道类型
            List<Dict> channelTypeList = DictUtil.getDictList(ZD.channelType);
            //审核状态
            List<Dict> approveStatusList = DictUtil.getDictList(ZD.channelApproveType);
            //渠道状态
            List<Dict> channelStatusList = DictUtil.getDictList(ZD.channelStatus);

            model.addAttribute("channelTypeList",channelTypeList);
            model.addAttribute("approveStatusList",approveStatusList);
            model.addAttribute("channelStatusList",channelStatusList);
            model.addAttribute("promotionChannel", promotionChannel);

        } catch (Exception e) {
            if (e instanceof TqException) {
                addFaildMessage(redirectAttributes, e.getMessage());
            } else {
                log.error("查询" + DESC + "异常", e);
            }
            return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
        }
        return ADDRESSPRE + "promotionChannelForm";
    }


    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(PromotionChannel promotionChannel) {
        OperatorCredential credential = getCredential();
        try {
            if (credential == null) {
                throw new RuntimeException("登录过期，请重新登录");
            }
            //参数验证
            checkParam(promotionChannel);
            //修改人、修改时间
            promotionChannel.setModifyOperatorId(credential.getOpId());
            promotionChannel.setModifyTime(new Date());
            if (promotionChannel.getId() == null) {
                //新增
                promotionChannel.setAddTime(promotionChannel.getModifyTime());
                promotionChannel.setAddOperatorId(credential.getOpId());
                promotionChannelService.addPromotionChannel(promotionChannel);
            } else {
                //修改
                promotionChannelService.updatePromotionChannel(promotionChannel);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.error("save" + DESC + "异常", e);
                return failJsonResult("系统异常");
            }
        }
    }

    private void checkParam(PromotionChannel promotionChannel) throws TqException {
        //投放渠道验证
        if (StringUtil.isEmpty(promotionChannel.getChannelName()) || promotionChannel.getChannelName().length() > 32){
            throw new TqException("投放渠道不能为空，且长度不能超过32位");
        }
        //渠道公司验证
        if ( promotionChannel.getCompanyName().length() > 64){
            throw new TqException("渠道公司长度不能超过64位");
        }

        if (StringUtil.isEmpty(promotionChannel.getChannelCode()) || promotionChannel.getChannelCode().length() > 32){
            throw new TqException("编码不能为空，且长度不能超过32位");
        }
        PromotionChannel codeChannel = promotionChannelService.getByCode(promotionChannel.getChannelCode());
        if (codeChannel != null){
            if (promotionChannel.getId() == null || !promotionChannel.getId().equals(codeChannel.getId())){
                throw new TqException("渠道编码已存在");
            }
        }
    }

    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Integer id) {
            try {
                OperatorCredential credential = getCredential();
                if (credential == null) {
                    throw new TqException("登录过期，请重新登录");
                }
                if( id == null){
                    throw new TqException("参数为空");
                }
                PromotionChannel promotionChannel = promotionChannelService.getById(id);
                if( promotionChannel == null ){
                    throw new TqException("渠道不存在");
                }
                promotionChannel.setModifyTime(new Date());
                promotionChannel.setModifyOperatorId(credential.getOpId());
                promotionChannelService.delete(promotionChannel);
                return simpleSuccessJsonResult("success");

            } catch (Exception e) {
                if (e instanceof TqException) {
                    return failJsonResult(e.getMessage());
                }else{
                    log.error("delete渠道异常", e);
                    return failJsonResult("delete渠道异常");
                }
            }
        }


}