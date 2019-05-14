package com.duobei.console.web.controller.push;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.banner.domain.Banner;
import com.duobei.core.operation.banner.domain.criteria.BannerCriteria;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;
import com.duobei.core.operation.push.service.PushRecordService;
import com.duobei.dic.ZD;
import com.duobei.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
@Controller
@RequestMapping(value = "${authzPath}/push")
@Slf4j
public class PushController extends BaseController {

    @Autowired
    private PushRecordService recordService;

    /**
     * 推送列表页
     * @param model
     * @return
     */
    @RequiresPermissions("push:list:view")
    @RequestMapping(value = "/list")
    public String list(Model model, Integer appId){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "push/message/list";
    }

    /**
     * ajax查询轮播列表
     * @return
     */
    @RequiresPermissions("push:list:view")
    @RequestMapping(value = "/getMessageData")
    @ResponseBody
    public String getMessageData(PushRecordCriteria criteria ){
        try {
            OperatorCredential credential = getCredential();
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }
            //验证数据权限
            validAuthData(criteria.getProductId());
            if (criteria.getPagesize()== BizConstant.INT_ZERO) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<PushRecordVo> list = recordService.getPageList(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("查询列表异常",e);
                return failJsonResult("查询失败");
            }
        }
    }

    /**
     * 跳转编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions("market:coupon:edit")
    @RequestMapping(value = "/form")
    public String form( Model model,Integer productId) throws TqException {
        model.addAttribute("productId", productId);
        model.addAttribute("appList", getAppListByProductId(productId));
        return "market/coupon/form";
    }

    @RequiresPermissions("push:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(PushRecord record ){
        try {
            OperatorCredential credential = getCredential();
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }
            record.setAddOperatorId(credential.getOpId());
            return simpleSuccessJsonResult("success");
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("save异常", e);
                return failJsonResult("save异常");
            }
        }
    }
}
