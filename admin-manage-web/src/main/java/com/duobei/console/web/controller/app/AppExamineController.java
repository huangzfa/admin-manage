package com.duobei.console.web.controller.app;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.operation.app.domain.vo.AppExamineVo;
import com.duobei.core.operation.app.service.AppExamineService;
import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.channel.service.PromotionChannelService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.dic.ZD;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/app/examine")
public class AppExamineController extends BaseController{
    private final static String PERMISSIONPRE = "app:examine:";
    private final static String ADDRESSPRE = "app/examine/";
    private final static String DESC = "app审核";

    @Resource
    AppExamineService appExamineService;

    @Resource
    PromotionChannelService promotionChannelService;
    /**
     *显示
     * @return
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list(Integer productId , Model model) {
        //获取用户产品列表
        List<Product> productList = getCredential().getProductList();
        if (productId == null && productList != null && productList.size() > 0){
            //如果未传productId 则赋予初始值
            productId = productList.get(0).getId();
        }
        model.addAttribute("productLists", JSON.toJSONString(productList));
        model.addAttribute("productId",productId);
        return ADDRESSPRE+"list";
    }

    /**
     * 数据查询
     * @param appExamineCriteria
     * @return
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/appExamList")
    public String appExamList(AppExamineCriteria appExamineCriteria) {
        //验证数据权限
        if( appExamineCriteria.getProductId() !=null ){
            try {
                if (appExamineCriteria.getAppId() == null){
                    validAuthData(appExamineCriteria.getProductId());
                }else{
                    validAuthData(appExamineCriteria.getProductId(),appExamineCriteria.getAppId());
                }

            }catch (Exception e){
                return failJsonResult(e.getMessage());
            }

        }else{
            return failJsonResult("产品数据查询失败");

        }
        if (appExamineCriteria.getPagesize() == 0) {
            appExamineCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {

            Map<String,Object> dataMap = new HashMap<>();
            //应用查询条件
            List<App> appList = new ArrayList<>();
            List<Integer> appIds = new ArrayList<>();
            Map<Integer,App> appMap = new HashMap<>();
            for(App app : getCredential().getAppList()){
                if (app.getProductId() == appExamineCriteria.getProductId()){
                    appList.add(app);
                    appIds.add(app.getId());
                    appMap.put(app.getId(),app);
                }
            }
            dataMap.put("appList",appList);
            //如果查询全部应用，则查询该用户目前拥有的应用操作权限的信息
            if (appExamineCriteria.getAppId() == null){
                appExamineCriteria.setAppIds(appIds);
            }
            //查询数据
            ListVo<AppExamineVo> list = appExamineService.getListVoByQuery(appExamineCriteria);
            for (AppExamineVo appExamineVo : list.getRows()){
                //应用名称
                appExamineVo.setAppName(appMap.get(appExamineVo.getAppId()).getAppName());
            }
            dataMap.put("list",list);
            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("查询列表异常", e);
                return failJsonResult("查询异常，请查看错误日志");
            }
        }
    }


    /**
     * 跳转编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions(PERMISSIONPRE+"edit")
    @RequestMapping(value = "/form")
    public String form(Model model, Integer id) throws TqException {

        //产品下拉框
        model.addAttribute("productList",getCredential().getProductList());
        //应用下拉框
        model.addAttribute("appList",getCredential().getAppList());
        //应用市场渠道下拉框
        model.addAttribute("channelList",promotionChannelService.getChannelListByType(1));
        if ( id !=null ) {
            //查询app审核详情
            model.addAttribute("appExamine", appExamineService.getById(id));
        }else{
            throw new TqException("系统异常");
        }
        return ADDRESSPRE+"/form";
    }
    /**
     * 删除
     * @param id
     * @return
     * @throws TqException
     */
    @RequiresPermissions( PERMISSIONPRE+"edit")
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
            AppExamine appExamine = appExamineService.getById(id);
            if( appExamine == null ){
                throw new TqException(DESC+"不存在");
            }
            appExamine.setModifyTime(new Date());
            appExamine.setModifyOperatorId(credential.getOpId());
            appExamineService.delete(appExamine);
            return simpleSuccessJsonResult("success");

        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("delete"+DESC+"异常", e);
                return failJsonResult("delete\"+DESC+\"异常");
            }
        }
    }
}
