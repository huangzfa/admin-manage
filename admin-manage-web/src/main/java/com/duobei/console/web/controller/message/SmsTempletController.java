package com.duobei.console.web.controller.message;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import com.duobei.core.message.sms.domain.SmsTemplet;
import com.duobei.core.message.sms.domain.criteria.SmsAppChannelConfigCriteria;
import com.duobei.core.message.sms.domain.criteria.SmsTempletCriteria;
import com.duobei.core.message.sms.domain.vo.SmsAppChannelConfigVo;
import com.duobei.core.message.sms.domain.vo.SmsTempletVo;
import com.duobei.core.message.sms.service.SmsTempletService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
@Controller
@RequestMapping(value = "${authzPath}/message/templet")
@Slf4j
public class SmsTempletController extends BaseController{

    @Autowired
    private SmsTempletService templetService;
    /**
     * 短信模板列表页
     * @param model
     * @return
     */
    @RequiresPermissions("templet:list:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "message/templet/list";
    }

    /**
     * ajax查短信模板列表
     * @return
     */
    @RequiresPermissions("templet:list:view")
    @RequestMapping(value = "/getData")
    @ResponseBody
    public String getProductData(SmsTempletCriteria criteria){
        try {
            if( criteria == null ){
                criteria = new SmsTempletCriteria();
            }
            if (criteria.getPagesize()== BizConstant.INT_ZERO) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            criteria.setAppList(getAppListByProductId(criteria.getProductId()));
            ListVo<SmsTempletVo> list = templetService.getPage(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("查询列表异常",e);
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
    @RequiresPermissions("templet:list:edit")
    @RequestMapping(value = "/form")
    public String form( Model model,Long id) throws TqException {
        if( id!=null){
            //短信开通类型
            model.addAttribute("templet",templetService.getById(id));
        }
        return "message/templet/form";
    }


    /**
     *
     * @param config
     * @return
     */
    @RequiresPermissions("templet:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String getProductData(SmsTemplet config){
        try {
            OperatorCredential criteria = getCredential();
            if( criteria == null ){
                throw new TqException("登陆过期，请重新登陆");
            }
            config.setCreateUser(criteria.getRealName());
            if(config.getId() == null ){
                templetService.save(config);
            }else{
                config.setUpdateTime(new Date());
                config.setUpdateUser(criteria.getRealName());
                templetService.update(config);
            }
            return simpleSuccessJsonResult("success");
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("保存异常",e);
                return failJsonResult("保存失败");
            }
        }
    }

}
