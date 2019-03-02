package com.duobei.console.web.controller.auth;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.encrypt.MD5Util;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;

import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.product.domain.AuthConfig;
import com.duobei.core.operation.product.domain.criteria.AuthConfigCriteria;
import com.duobei.core.operation.product.service.AuthConfigService;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**认证项配置控制类
 * @author huangzhongfa
 * @date 2018/12/3
 */
@Controller
@RequestMapping(value = "${authzPath}/product/authConfig")
public class AuthConfigController extends com.duobei.console.web.controller.base.BaseController {

    private final static Logger log = LoggerFactory.getLogger(AuthConfigController.class);
    @Resource
    private AuthConfigService authConfigService;

    /**
     * 认证项列表页
     * @param model
     * @return
     */
    @RequiresPermissions("product:authConfig:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        return "product/authConfig/authConfigList";
    }

    /**
     * ajax查询认证项列表
     * @return
     */
    @RequiresPermissions("product:authConfig:view")
    @RequestMapping(value = "/getAuthConfigData")
    @ResponseBody
    public String getAuthConfigData(AuthConfigCriteria criteria){
        try {
            if( criteria == null ){
                criteria = new AuthConfigCriteria();
            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<AuthConfig> list = authConfigService.getLists(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            log.warn("认证列表异常",e);
            return failJsonResult("查询认证列表异常，请查看错误日志");
        }
    }

    /**
     * 跳转编辑页
     * @param model
     * @param authCode
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:authConfig:edit")
    @RequestMapping(value = "/form")
    public String form(Model model, String authCode) throws TqException {

        if (StringUtil.isNotEmpty(authCode)) {
            model.addAttribute("authConfig", authConfigService.getByCode(authCode));
        }else{
            model.addAttribute("authConfig", new AuthConfig());
        }
        model.addAttribute("authTypes", DictUtil.getDictList(ZD.authType));
        model.addAttribute("pageTypes", DictUtil.getDictList(ZD.aPMenuType));
        return "product/authConfig/authForm";
    }

    /**
     * 保存或修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "product:authConfig:edit" })
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(AuthConfig entity) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if (entity.getId() == null || entity.getId().equals(0)) {
                entity.setAuthCode(MD5Util.encrypt(DateUtil.getTimeStr(new Date())));
                authConfigService.save(entity);
            } else {
                entity.setModifyTime(new Date());
                authConfigService.update(entity);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("save项配置异常", e);
                return failJsonResult("save认证配置项异常");
            }

        }

    }

    /**
     * 修改认证项状态
     * @param authCode
     * @return
     */
    @RequiresPermissions({ "product:authConfig:edit" })
    @RequestMapping(value = "/editState")
    @ResponseBody
    public String editState(String authCode,String authState) throws RuntimeException{
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new RuntimeException("登录过期，请重新登录");
            }
            if( StringUtil.isBlank(authCode) || StringUtil.isBlank(authState)){
                throw new RuntimeException("参数为空");
            }
            authConfigService.editState(authCode,authState);
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("editState认证配置项异常", e);
                return failJsonResult("editState认证配置项失败");
            }

        }
    }
}
