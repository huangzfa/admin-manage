package com.duobei.console.web.controller.message;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.SmsUserfulCodeEnums;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import com.duobei.core.message.sms.domain.criteria.SmsAppChannelConfigCriteria;
import com.duobei.core.message.sms.domain.vo.SmsAppChannelConfigVo;
import com.duobei.core.message.sms.service.SmsAppChannelConfigService;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
@Controller
@RequestMapping(value = "${authzPath}/message/sms")
@Slf4j
public class SmsChannelConfigController extends BaseController{

    @Autowired
    private SmsAppChannelConfigService channelConfigService;
    @Autowired
    private AppService appService;
    /**
     * 渠道管理列表页
     * @param model
     * @return
     */
    @RequiresPermissions("message:sms:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        return "message/sms/channelList";
    }

    /**
     * ajax查渠道管理列表
     * @return
     */
    @RequiresPermissions("message:sms:view")
    @RequestMapping(value = "/getChannelConfig")
    @ResponseBody
    public String getProductData(SmsAppChannelConfigCriteria criteria){
        try {
            if( criteria == null ){
                criteria = new SmsAppChannelConfigCriteria();
            }
            if (criteria.getPagesize()==BizConstant.INT_ZERO) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            List<App> appList = getCredential().getAppList();
            List<String> appKeyList = appList.stream().map(App::getAppKey).collect(Collectors.toList());
            criteria.setAppKeyList(appKeyList);
            ListVo<SmsAppChannelConfigVo> list = channelConfigService.getPage(criteria,appList);
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
    @RequiresPermissions("message:sms:edit")
    @RequestMapping(value = "/form")
    public String form( Model model,Long id) throws TqException {
        if( id!=null){
            //短信开通类型
            model.addAttribute("channel",channelConfigService.getById(id));
        }
        model.addAttribute("appList",getCredential().getAppList());
        return "message/sms/channelForm";
    }


    /**
     *
     * @param config
     * @return
     */
    @RequiresPermissions("message:sms:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String getProductData(SmsAppChannelConfig config){
        try {
            OperatorCredential criteria = getCredential();
            if( criteria == null ){
                throw new TqException("登陆过期，请重新登陆");
            }
            config.setCreateUser(criteria.getRealName());
            if(config.getId() == null ){
                channelConfigService.save(config);
            }else{
                config.setUpdateTime(new Date());
                config.setUpdateUser(criteria.getRealName());
                channelConfigService.update(config);
            }
            return simpleSuccessJsonResult("success");
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("保存列表异常",e);
                return failJsonResult("保存失败");
            }
        }
    }

    @RequiresPermissions("message:sms:edit")
    @RequestMapping(value = "/editState")
    @ResponseBody
    public String editState(SmsAppChannelConfig config){
        try {
            OperatorCredential criteria = getCredential();
            if( criteria == null ){
                throw new TqException("登陆过期，请重新登陆");
            }
            config.setUpdateTime(new Date());
            config.setUpdateUser(criteria.getRealName());
            channelConfigService.editState(config);
            return simpleSuccessJsonResult("success");
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("修改smsChannel异常",e);
                return failJsonResult("修改失败");
            }
        }
    }
}
