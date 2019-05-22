package com.duobei.console.web.controller.message;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.push.domain.PushConfig;
import com.duobei.core.message.push.domain.criteria.PushConfigCriteria;
import com.duobei.core.message.push.service.PushConfigService;
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
@RequestMapping(value = "${authzPath}/message/push")
@Slf4j
public class PushConfigController extends BaseController{

    @Autowired
    private PushConfigService pushConfigService;

    /**
     * 渠道管理列表页
     * @param model
     * @return
     */
    @RequiresPermissions("message:push:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        return "message/push/list";
    }

    /**
     * ajax查渠道管理列表
     * @return
     */
    @RequiresPermissions("message:push:view")
    @RequestMapping(value = "/getPushConfig")
    @ResponseBody
    public String getProductData(PushConfigCriteria criteria){
        try {
            if( criteria == null ){
                criteria = new PushConfigCriteria();
            }
            if (criteria.getPagesize()== BizConstant.INT_ZERO) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<PushConfig> list = pushConfigService.getPage(criteria);
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
    @RequiresPermissions("message:push:edit")
    @RequestMapping(value = "/form")
    public String sign( Model model,Long id) throws TqException {
        if( id!=null){
            model.addAttribute("push",pushConfigService.getById(id));
        }
        return "message/push/form";
    }

    /**
     *
     * @param config
     * @return
     */
    @RequiresPermissions("message:push:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String getProductData(PushConfig config){
        try {
            OperatorCredential criteria = getCredential();
            if( criteria == null ){
                throw new TqException("登陆过期，请重新登陆");
            }
            config.setCreateUser(criteria.getRealName());
            if(config.getId() == null ){
                pushConfigService.save(config);
            }else{
                config.setUpdateTime(new Date());
                config.setUpdateUser(criteria.getRealName());
                pushConfigService.update(config);
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

    @RequiresPermissions("message:push:edit")
    @RequestMapping(value = "/editState")
    @ResponseBody
    public String editState(PushConfig config){
        try {
            OperatorCredential criteria = getCredential();
            if( criteria == null ){
                throw new TqException("登陆过期，请重新登陆");
            }
            config.setUpdateTime(new Date());
            config.setUpdateUser(criteria.getRealName());
            pushConfigService.editState(config);
            return simpleSuccessJsonResult("success");
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("pushConfig修改异常",e);
                return failJsonResult("修改失败");
            }
        }
    }
}
