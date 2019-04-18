package com.duobei.console.web.controller.finance;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.operation.zfb.domain.ZfbAccountGuide;
import com.duobei.core.operation.zfb.domain.criteria.ZfbAccountCriteria;
import com.duobei.core.operation.zfb.domain.vo.ZfbAccountVo;
import com.duobei.core.operation.zfb.service.ZfbAccountGuideService;
import com.duobei.core.operation.zfb.service.ZfbAccountService;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
@Controller
@RequestMapping(value = "${authzPath}/finance/zfbAccount")
public class ZfbAccountController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(
            ZfbAccountController.class);

    private final static String PERMISSIONPRE = "finance:zfbAccount:";
    private final static String ADDRESSPRE = "finance/zfbAccount/";
    private final static String DESC = "支付宝账号";

    @Resource
    private ZfbAccountService zfbAccountService;

    @Autowired
    private ZfbAccountGuideService zfbAccountGuideService;


    @RequiresPermissions(PERMISSIONPRE + "view")
    @RequestMapping(value = "/list")
    public String list(Integer productId,Model model){
    //获取用户产品列表
    List<Product> productList = getCredential().getProductList();
        if (productId == null && productList != null && productList.size() > 0){
        //如果未传productId 则赋予初始值
        productId = productList.get(0).getId();
    }
        model.addAttribute("productLists", JSON.toJSONString(productList));
        model.addAttribute("productId",productId);
        return ADDRESSPRE + "zfbAccountList";
    }

    @RequiresPermissions(PERMISSIONPRE + "view")
    @ResponseBody
    @RequestMapping(value = "/zfbAccountList")
    public String zfbAccountList(ZfbAccountCriteria zfbAccountCriteria) {
        OperatorCredential credential = getCredential();
        //验证用户权限
        try {
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }
            //验证数据权限
            if( zfbAccountCriteria.getProductId() !=null ){
                try {
                    validAuthData(zfbAccountCriteria.getProductId());
                }catch (Exception e){
                    return failJsonResult(e.getMessage());
                }
            }else{
                return failJsonResult("应用数据查询失败");
            }
        if (zfbAccountCriteria.getPagesize() == 0) {
            zfbAccountCriteria.setPagesize(GlobalConfig.getPageSize());
        }
            ListVo<ZfbAccount> list = zfbAccountService
                    .queryZfbAccountList(zfbAccountCriteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("查询" + DESC + "列表异常", e);
                return failJsonResult("查询" + DESC + "列表异常，请查看错误日志");
            }
        }
    }

    @RequiresPermissions(PERMISSIONPRE + "view")
    @RequestMapping(value = "/form")
    public String form( ZfbAccount zfbAccount, Model model) {
        ZfbAccountVo zfbAccountVo = new ZfbAccountVo();
            if (zfbAccount.getId() != null) {
                zfbAccount = zfbAccountService.queryZfbAccountById(zfbAccount.getId());
                List<ZfbAccountGuide> zfbAccountGuideList = zfbAccountGuideService
                        .queryZfbAccountGuideByZfbAccountId(zfbAccount.getId());
                StringBuilder imgUrls = new StringBuilder();
                boolean flag = true;
                for (ZfbAccountGuide zfbAccountGuide : zfbAccountGuideList) {
                    if (flag) {
                        flag = false;
                    } else {
                        imgUrls.append(",");
                    }
                    imgUrls.append(zfbAccountGuide.getImgUrl());
                }
                BeanUtils.copyProperties(zfbAccount, zfbAccountVo);
                zfbAccountVo.setImgUrls(imgUrls.toString());
            }
            zfbAccountVo.setProductId(zfbAccount.getProductId());
        model.addAttribute("zfbAccountVo", zfbAccountVo);
        return ADDRESSPRE + "zfbAccountForm";
    }


    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(ZfbAccountVo zfbAccountVo) {
        OperatorCredential credential = getCredential();
        try {
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //验证数据权限
            if( zfbAccountVo.getProductId() !=null ){
                validAuthData(zfbAccountVo.getProductId());
            }else{
                throw new TqException("数据操作权限不足");
            }
            //验证参数
            validParam(zfbAccountVo);
            zfbAccountVo.setModifyOperatorId(credential.getOpId());
            zfbAccountVo.setModifyTime(new Date());
            if (zfbAccountVo.getId() == null) {
                zfbAccountVo.setAddOperatorId(zfbAccountVo.getModifyOperatorId());
                zfbAccountVo.setAddTime(zfbAccountVo.getModifyTime());
                zfbAccountService.save(zfbAccountVo);
            } else {
                zfbAccountService.update(zfbAccountVo);
            }
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("编辑"+DESC+"异常", e);
                return failJsonResult("编辑"+DESC+"异常");
            }
        }
        return simpleSuccessJsonResult("suuccess");
    }

    private void validParam(ZfbAccountVo zfbAccountVo) throws TqException {
        //账号名称
        if (StringUtil.isEmpty(zfbAccountVo.getName()) || zfbAccountVo.getName().length() > 32){
            throw new TqException("账号名称不能为空,且长度应小于32位");
        }
        //支付宝账号
        if (StringUtil.isEmpty(zfbAccountVo.getAccount()) || zfbAccountVo.getAccount().length() > 32){
            throw new TqException("支付宝账号不能为空,且长度应小于32位");
        }
        //账户名称
        if (StringUtil.isEmpty(zfbAccountVo.getQrcode())){
            throw new TqException("请上传二维码");
        }
        //账户名称
        if (StringUtil.isEmpty(zfbAccountVo.getImgUrls())){
            throw new TqException("请上传支付宝教程图");
        }
    }

    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(ZfbAccount zfbAccount) {
        OperatorCredential credential = getCredential();
        try {
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //验证数据权限
            if( zfbAccount.getProductId() !=null ){
                validAuthData(zfbAccount.getProductId());
            }else{
                throw new TqException("数据操作权限不足");
            }
            zfbAccount.setModifyOperatorId(credential.getOpId());
            zfbAccount.setModifyTime(new Date());
            zfbAccountService.deleteZfbAccount(zfbAccount);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("删除"+DESC+"异常", e);
                return failJsonResult("删除"+DESC+"异常");
            }
        }
        return simpleSuccessJsonResult("suuccess");
    }

    @RequiresPermissions({PERMISSIONPRE + "edit"})
    @ResponseBody
    @RequestMapping(value = "/updateState")
    public String updateState(ZfbAccount zfbAccount) {
        OperatorCredential credential = getCredential();

        try {
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //验证数据权限
            if( zfbAccount.getProductId() !=null ){
                validAuthData(zfbAccount.getProductId());
            }else{
                throw new TqException("数据操作权限不足");
            }
            zfbAccount.setModifyOperatorId(credential.getOpId());
            zfbAccount.setModifyTime(new Date());
            zfbAccountService.updateStatus(zfbAccount);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("修改"+DESC+"状态异常", e);
                return failJsonResult("修改"+DESC+"状态异常");
            }
        }
        return simpleSuccessJsonResult("suuccess");
    }
}
