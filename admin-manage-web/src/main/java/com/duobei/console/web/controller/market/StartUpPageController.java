package com.duobei.console.web.controller.market;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.service.ProductService;
import com.duobei.core.operation.startupPage.domain.StartupPage;
import com.duobei.core.operation.startupPage.service.StartUpPageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;
/**
 * @author litianxiong
 * @description 启动页配置控制器
 * @date 2019/3/2
 */
@Controller
@RequestMapping(value = "${authzPath}/market/starupPage")
public class StartUpPageController extends BaseController {

    @Resource
    StartUpPageService startUpPageService;

    @Resource
    ProductService productService;
    /**
     * 启动页配置获取
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions("marker:starupPage:view")
    @RequestMapping(value = "/form")
    public String startupPageForm( Model model,Integer appId) throws TqException {
        OperatorCredential credential = getCredential();
        //获取用户应用权限
        List<App> appList = credential.getAppList();
        if (appList == null || appList.size() <= 0){
            return failJsonResult("无应用权限");
        }
        //应用列表

        if (appId == null){
            appId = appList.get(0).getId();
        }
        //查询应用
        StartupPage startupPage = startUpPageService.getByAppId(appId);
        model.addAttribute("appList",appList);
        model.addAttribute("appId",appId);
        model.addAttribute("startupPage",startupPage);
        return "market/startupPage/startupPageForm";
    }
    @RequiresPermissions("marker:starupPage:edit")
    @RequestMapping(value = "/update")
    public String update (StartupPage startupPage){
        return null;
    }
}
