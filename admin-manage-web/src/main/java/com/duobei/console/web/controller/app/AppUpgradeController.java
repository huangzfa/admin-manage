package com.duobei.console.web.controller.app;


import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.app.domain.AppUpgrade;
import com.duobei.core.operation.app.domain.criteria.AppUpgradeCriteria;
import com.duobei.core.operation.app.domain.vo.AppUpgradeVo;
import com.duobei.core.operation.app.service.AppUpgradeService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.dic.ZD;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/app/upgrade")
public class AppUpgradeController extends BaseController {
    private final static String PERMISSIONPRE = "app:upgrade:";
    private final static String ADDRESSPRE = "app/upgrade/";
    private final static String DESC = "app升级";

    @Resource
    AppUpgradeService appUpgradeService;
    /**
     *显示
     * @return
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list(Integer productId , Model model) {
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        model.addAttribute("appList",JSON.toJSONString(getCredential().getAppList()));
        return ADDRESSPRE+"list";
    }

    /**
     * 数据查询
     * @param criteria
     * @return
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/upgradeList")
    public String upgradeList(AppUpgradeCriteria criteria) {
        try {
            //验证数据权限
            try {
                validAuthData(criteria.getProductId(),criteria.getAppId());
            }catch (Exception e){
                return failJsonResult(e.getMessage());
            }
            if (criteria == null) {
                criteria = new AppUpgradeCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<AppUpgradeVo> list = appUpgradeService.getPage(criteria);
            return successJsonResult("success", "list", list);
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
        model.addAttribute("appList",JSON.toJSONString(getCredential().getAppList()));
        //系统下拉框
        model.addAttribute("osList",DictUtil.getDictList(ZD.osTypeString));
        //app升级状态
        model.addAttribute("stateList",DictUtil.getDictList(ZD.appUpgradeState));
        //app升级范围下拉框
        model.addAttribute("upgradeRangeList",DictUtil.getDictList(ZD.upgradeRangeState));
        //是否强制升级
        model.addAttribute("isForceList",DictUtil.getDictList(ZD.appUpgradeIsForce));
        //是否静默升级
        model.addAttribute("isSilenceList",DictUtil.getDictList(ZD.silenceUpgrade));

        if ( id !=null ) {
            //查询app升级详情
            AppUpgrade upgrade = appUpgradeService.getById(id);
            model.addAttribute("upgrade", upgrade);
        }else{
            model.addAttribute("upgrade", new AppUpgrade());
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
            AppUpgrade appUpgrade = appUpgradeService.getById(id);
            if( appUpgrade == null ){
                throw new TqException(DESC+"不存在");
            }
            appUpgrade.setModifyTime(new Date());
            appUpgrade.setModifyOperatorId(credential.getOpId());
            appUpgradeService.delete(appUpgrade);
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
    public String save(AppUpgrade entity) throws RuntimeException {
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
                appUpgradeService.save(entity);
            } else {
                //修改
                appUpgradeService.update(entity);
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

    private void validParam(AppUpgrade entity) throws TqException {
        if (entity.getProductId() == null){
            throw new TqException("产品未选择，请全部配置完成后保存");
        }
        if (entity.getAppId() == null){
            throw new TqException("应用未选择，请全部配置完成后保存");
        }
        if (entity.getAppOsType() == null){
            throw new TqException("系统未选择，请全部配置完成后保存");
        }
        if (entity.getVersionNumber() == null){
            throw new TqException("版本号未填写或格式填写有误，请全部配置完成后保存");
        }
        if(StringUtil.isEmpty(entity.getVersionRemark()) || entity.getVersionRemark().length() > 50){
            throw new TqException("版本描述不能为空且长度不能大于50字");
        }
        if (ZD.upgradeRangeState_part.equals(entity.getUpgradeRange())){
            if (entity.getMaxVersionNumber() == null || entity.getMinVersionNumber() == null){
                throw new TqException("部分版本升级需配置最小升级版本和最大升级版本");
            }
            if (entity.getMinVersionNumber() > entity.getMaxVersionNumber()){
                throw new TqException("最小升级版本不能大于最大升级版本");
            }
        }
    }
    @RequiresPermissions(PERMISSIONPRE+"edit")
    @ResponseBody
    @RequestMapping(value = "/editState")
    public String updateStatus(AppUpgrade entity){
        OperatorCredential credential = getCredential();
        try {
            entity.setModifyOperatorId(credential.getOpId());
            entity.setModifyTime(new Date());
                /**
                 * 修改状态
                 */
                appUpgradeService.updateStatus(entity);

        }catch (Exception e){
            log.error("修改{}状态失败{}",DESC,e.getMessage());
            return failJsonResult("修改"+DESC+"状态异常");
        }
        Map<String,Object> resultMap = new HashMap<>();
        return successJsonResult(resultMap);
    }


}
