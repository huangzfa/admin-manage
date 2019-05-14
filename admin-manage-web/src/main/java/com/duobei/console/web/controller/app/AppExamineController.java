package com.duobei.console.web.controller.app;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
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

        try {
             //验证数据权限
            if( appExamineCriteria.getProductId() !=null ){
                validAuthData(appExamineCriteria.getProductId(),appExamineCriteria.getAppId());
            }else{
                return failJsonResult("产品数据查询失败");
            }
            if (appExamineCriteria.getPagesize() == 0) {
                appExamineCriteria.setPagesize(GlobalConfig.getPageSize());
            }
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
                log.warn("查询"+DESC+"列表异常", e);
                return failJsonResult("查询"+DESC+"异常");
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
        //系统下拉框
        model.addAttribute("osList",DictUtil.getDictList(ZD.osTypeString));
        //应用市场渠道下拉框
        model.addAttribute("channelList",promotionChannelService.getChannelListByType(1));
        if ( id !=null ) {
            //查询app审核详情
            model.addAttribute("appExamine", appExamineService.getById(id));
        }else{
            model.addAttribute("appExamine", new AppExamine());
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
                return failJsonResult("delete"+DESC+"异常");
            }
        }
    }


    /**
     * 保存或修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ PERMISSIONPRE+"edit" })
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(AppExamine entity) throws RuntimeException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            validParam(entity);
            //验证数据权限
            validAuthData(entity.getProductId(),entity.getAppId());

            entity.setModifyTime(new Date());
            entity.setModifyOperatorId(credential.getOpId());
            if (entity.getId() == null || entity.getId().equals(0)) {
                entity.setAddOperatorId(credential.getOpId());
                entity.setAddTime(entity.getModifyTime());
                //新增
                appExamineService.save(entity);
            } else {
                //修改
                appExamineService.update(entity);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("编辑"+DESC+"异常", e);
                return failJsonResult("编辑"+DESC+"异常");
            }

        }

    }

    private void validParam(AppExamine entity) throws TqException {
        if (entity.getProductId() == null){
            throw new TqException("产品未选择，请全部配置完成后保存");
        }
        if (entity.getAppId() == null){
            throw new TqException("应用未选择，请全部配置完成后保存");
        }
        if (entity.getAppOsType() == null){
            throw new TqException("系统未选择，请全部配置完成后保存");
        }
        if (entity.getChannelId() == null){
            throw new TqException("渠道未选择，请全部配置完成后保存");
        }
        if (entity.getVersionNumber() == null){
            throw new TqException("版本号未填写或格式填写有误，请全部配置完成后保存");
        }
    }

}
