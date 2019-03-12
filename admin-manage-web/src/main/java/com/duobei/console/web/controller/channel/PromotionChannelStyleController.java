package com.duobei.console.web.controller.channel;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelStyleCriteria;
import com.duobei.core.operation.channel.domain.vo.PromotionChannelStyleVo;
import com.duobei.core.operation.channel.service.PromotionChannelStyleService;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/7
 */
@Controller
@RequestMapping(value = "${authzPath}/channel/style")
public class PromotionChannelStyleController extends BaseController {
    @Resource
    PromotionChannelStyleService promotionChannelStyleService;

    private final static Logger log = LoggerFactory.getLogger(
            PromotionChannelStyleController.class);

    private final static String PERMISSIONPRE = "channel:style:";
    private final static String ADDRESSPRE = "channel/style/";
    private final static String DESC = "样式";


    @RequiresPermissions(PERMISSIONPRE + "view")
    @RequestMapping(value = "/list")
    public String list() {
        return ADDRESSPRE + "promotionChannelStyleList";
    }

    @RequiresPermissions(PERMISSIONPRE + "view")
    @ResponseBody
    @RequestMapping(value = "/promotionChannelStyleList")
    public String getList(PromotionChannelStyleCriteria promotionChannelStyleCriteria) {
        OperatorCredential credential = getCredential();
        if (promotionChannelStyleCriteria.getPagesize() == 0) {
            promotionChannelStyleCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            ListVo<PromotionChannelStyleVo> list = promotionChannelStyleService.getStyleListByQuery(promotionChannelStyleCriteria);
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
    public String form(@ModelAttribute("channelStyle") PromotionChannelStyle promotionChannelStyle,
                       Model model, RedirectAttributes redirectAttributes) {
        try {
            if (promotionChannelStyle.getId() != null) {
                promotionChannelStyle = promotionChannelStyleService.getById(promotionChannelStyle.getId());
            }else{
               //新建默认  按钮模板：方框模板 下载页链接：不更改
                promotionChannelStyle.setButtonTemplate(ZD.channelStylebtTempt_square);
                promotionChannelStyle.setDownloadPageType(ZD.downloadPageType_no);
            }
           //审按钮模板
            List<Dict> buttonTempTypeList = DictUtil.getDictList(ZD.channelStylebtTempt);
            //渠道状态
            List<Dict> downloadPageTypeList = DictUtil.getDictList(ZD.downloadPageType);

            model.addAttribute("buttonTempTypeList",buttonTempTypeList);
            model.addAttribute("downloadPageTypeList",downloadPageTypeList);
            model.addAttribute("channelStyle", promotionChannelStyle);

        } catch (Exception e) {
            if (e instanceof TqException) {
                addFaildMessage(redirectAttributes, e.getMessage());
            } else {
                log.error("查询" + DESC + "异常", e);
                addFaildMessage(redirectAttributes, "查询" + DESC + "异常，请查看错误日志");
            }
            return "redirect:" + this.authzPath + "/" + ADDRESSPRE + "list";
        }
        return ADDRESSPRE + "promotionChannelStyleForm";
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
            PromotionChannelStyle promotionChannelStyle = promotionChannelStyleService.getById(id);
            if( promotionChannelStyle == null ){
                throw new TqException("样式不存在");
            }
            promotionChannelStyle.setModifyTime(new Date());
            promotionChannelStyle.setModifyOperatorId(credential.getOpId());
            promotionChannelStyleService.delete(promotionChannelStyle);
            return simpleSuccessJsonResult("success");

        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("删除样式异常", e);
                return failJsonResult("删除样式异常");
            }
        }
    }

    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(PromotionChannelStyle promotionChannelStyle) {
        OperatorCredential credential = getCredential();
        try {
            if (credential == null) {
                throw new RuntimeException("登录过期，请重新登录");
            }
            //参数验证
            checkParam(promotionChannelStyle);
            //修改人、修改时间
            promotionChannelStyle.setModifyOperatorId(credential.getOpId());
            promotionChannelStyle.setModifyTime(new Date());
            promotionChannelStyle.setModelType(ZD.channelModelType_promotion);
            if (promotionChannelStyle.getId() == null) {
                //新增
                promotionChannelStyle.setAddTime(promotionChannelStyle.getModifyTime());
                promotionChannelStyle.setAddOperatorId(credential.getOpId());
                promotionChannelStyleService.addStyle(promotionChannelStyle);
            } else {
                //修改
                promotionChannelStyleService.updateStyle(promotionChannelStyle);
            }
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.error("save" + DESC + "异常", e);
                return failJsonResult("系统异常");
            }
        }
        return simpleSuccessJsonResult("success");
    }

    private void checkParam(PromotionChannelStyle promotionChannelStyle) throws TqException {
        //样式名称验证
        if (StringUtil.isEmpty(promotionChannelStyle.getStyleName()) || promotionChannelStyle.getStyleName().length() > 12){
            throw new TqException("样式名称不能为空，且长度不能超过12位");
        }
        //文案认证
        if (StringUtil.isEmpty(promotionChannelStyle.getButtonText()) || promotionChannelStyle.getButtonText().length() > 10){
            throw new TqException("按钮文案不能为空，且长度不能超过10位");
        }
        //色号验证
        if (StringUtil.isEmpty(promotionChannelStyle.getButtonBackground()) ){
            throw new TqException("请输入色号");
        }else{
            //颜色正则表达式
            String reg1 = "^#[0-9a-fA-F]{6}$";
            String reg2 = "^[rR][gG][Bb][\\(]([\\s]*(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?)[\\s]*,){2}[\\s]*(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)[\\s]*[\\)]{1}$";
         if (!Pattern.matches(reg1 ,promotionChannelStyle.getButtonBackground()) && !Pattern.matches(reg2 ,promotionChannelStyle.getButtonBackground())){
                 throw new TqException("请输入正确的色号");

        }
        //自定义链接验证
        if (ZD.downloadPageType_custom == promotionChannelStyle.getDownloadPageType()){
            if (StringUtil.isEmpty(promotionChannelStyle.getDownloadPageUrl())){
                throw new TqException("请输入自定义链接");
                }
            }
        }
    }
}
