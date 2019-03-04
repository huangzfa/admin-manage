package com.duobei.console.web.controller.market;

import com.duobei.common.exception.TqException;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.product.service.ProductService;
import com.duobei.core.operation.startupPage.domain.StartupPage;
import com.duobei.core.operation.startupPage.service.StartupPageService;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * @author litianxiong
 * @description 启动页配置控制器
 * @date 2019/3/2
 */
@Controller
@RequestMapping(value = "${authzPath}/market/startupPage")
public class StartUpPageController extends BaseController {
    Logger log = LoggerFactory.getLogger(StartUpPageController.class);
    @Resource
    StartupPageService startupPageService;

    @Resource
    AppService appService;
    /**
     * 启动页配置获取
     * @param model
     *
     * @throws TqException
     */
    @RequiresPermissions("marker:startupPage:view")
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
        StartupPage startupPage = startupPageService.getByAppId(appId);
        if (startupPage == null){
            model.addAttribute("startupPage",new StartupPage());
        }else{
            model.addAttribute("startupPage",startupPage);
        }
        model.addAttribute("appList",appList);
        model.addAttribute("appId",appId);

        return "market/startupPage/startupPageForm";
    }
    @RequiresPermissions("marker:startupPage:edit")
    @RequestMapping(value = "/update")
    public String update (StartupPage startupPage){

        //获取用户应用权限
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new RuntimeException("登录过期，请重新登录");
            }
            //验证数据权限
            if( startupPage.getAppId() !=null ){
                validAuthData(null,startupPage.getAppId());
            }else{
                throw new TqException("数据操作权限失败");
            }
            //修改时间、修改人
            startupPage.setModifyOperatorId(credential.getOpId());
            startupPage.setModifyTime(new Date());
            if (startupPage.getId() == null){
                App app = appService.getAppById(startupPage.getAppId());
                startupPage.setProductId(app.getProductId());
                //新增
                //添加人、添加时间、默认链接类型为url
                startupPage.setAddOperatorId(credential.getOpId());
                startupPage.setAddTime(startupPage.getModifyTime());
                startupPage.setRedirectType(ZD.redirectType_url);
                startupPageService.save(startupPage);
            }else {
                //修改
                startupPageService.updateById(startupPage);
            }
        } catch (Exception e) {
            log.warn("编辑启动页异常", e);
            return failJsonResult("编辑启动页异常");

        }
        return null;

    }
}
