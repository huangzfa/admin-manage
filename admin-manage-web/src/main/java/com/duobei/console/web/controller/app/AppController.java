package com.duobei.console.web.controller.app;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.domain.criteria.AppCriteria;
import com.duobei.core.operation.app.domain.vo.AppVo;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.service.MerchantService;
import com.duobei.core.operation.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/4
 */
@Controller
@RequestMapping(value = "${authzPath}/app")
@Slf4j
public class AppController  extends BaseController {

    @Autowired
    private AppService appService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MerchantService merchantService;

    /**
     * app列表页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("app:list:view")
    @RequestMapping(value = "/list")
    public String list(Model model) {
        return "app/list";
    }

    @RequiresPermissions("app:list:view")
    @RequestMapping(value = "/form")
    public String form(Model model,Integer id) {
        if( id!=null){
            App app = appService.getAppById(id);
            model.addAttribute("app",app);
            model.addAttribute("productId",app.getProductId());
        }
        List<Merchant> merchantList = merchantService.getByProductIds(getCredential().getProductList());
        model.addAttribute("merchantList",merchantList);
        return "app/form";
    }

    /**
     * ajax查询
     *
     * @return
     */
    @RequiresPermissions("app:list:view")
    @RequestMapping(value = "/getAppData")
    @ResponseBody
    public String getProductData(AppCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new AppCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            List<Integer> productList = getCredential().getProductList().stream().map(Product::getId).collect(Collectors.toList());
            criteria.setProductList(productList);
            ListVo<AppVo> list = appService.getLists(criteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("ajax查询产品列表异常", e);
                return failJsonResult("查询失败");
            }
        }
    }

    @RequiresPermissions("app:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(App app) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( app.getId() == null ){
                app.setAddOperatorId(credential.getOpId());
                appService.save(app,credential);
            }else{
                app.setModifyTime(new Date());
                app.setModifyOperatorId(credential.getOpId());
                appService.update(app,credential);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save异常", e);
                return failJsonResult("save异常");
            }
        }
    }

    @RequiresPermissions("app:list:edit")
    @RequestMapping(value = "/editState")
    @ResponseBody
    public String editState(App app) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            app.setModifyTime(new Date());
            app.setModifyOperatorId(credential.getOpId());
            appService.update(app,credential);
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("状态修改异常", e);
                return failJsonResult("状态修改异常");
            }
        }
    }

    @RequiresPermissions("app:list:edit")
    @RequestMapping(value = "/getProductByMerchantId")
    @ResponseBody
    public String getProductByMerchantId(Integer merchantId) {
        ProductCriteria criteria = new ProductCriteria();
        criteria.setPagesize(100);
        criteria.setMerchantId(merchantId);
        return successJsonResult("success","list",productService.getLists(criteria).getRows());
    }
}
