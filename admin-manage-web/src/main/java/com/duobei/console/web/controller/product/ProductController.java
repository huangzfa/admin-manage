package com.duobei.console.web.controller.product;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import com.duobei.core.operation.product.service.MerchantService;
import com.duobei.core.operation.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author huangzhongfa
 * @description 产品管理
 * @date 2019/2/26
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private MerchantService merchantService;

    /**
     * 商户产品列表页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:view")
    @RequestMapping(value = "/mpList")
    public String mpList(Model model) {
        model.addAttribute("merchantList", JSON.toJSONString(merchantService.getAll()));
        return "product/mpList";
    }

    /**
     * 产品列表页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:view")
    @RequestMapping(value = "/pList")
    public String pList(Model model) {
        model.addAttribute("merchantList", JSON.toJSONString(merchantService.getAll()));
        return "product/pList";
    }

    /**
     * ajax查询产品列表
     *
     * @return
     */
    @RequiresPermissions("product:list:view")
    @RequestMapping(value = "/getProductData")
    @ResponseBody
    public String getProductData(ProductCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new ProductCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<ProductVo> list = productService.getLists(criteria);
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

    /**
     * 产品表单添加页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/mpForm")
    public String mpForm(Model model, String productCode) {
        if (StringUtil.isBlank(productCode)) {
            model.addAttribute("product", productService.getByCode(productCode));
        }
        model.addAttribute("merchants",merchantService.getAll());
        return "product/mpForm";
    }

    /**
     * 产品表单添加页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/pForm")
    public String pForm(Model model, String productCode) {
        if (StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            if( product !=null ){
                model.addAttribute("mechantName",merchantService.getById(product.getMerchantId()).getMerchantName());
            }
        }
        return "product/pForm";
    }

    /**
     * 产品表单添加页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/pConfig")
    public String pConfig(Model model, String productCode) {
        if (StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            if( product !=null ){
                model.addAttribute("mechantName",merchantService.getById(product.getMerchantId()).getMerchantName());
            }
        }
        return "product/pForm";
    }

    /**
     * 产品保存修改操作
     *
     * @param request
     * @param product
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, Product product) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if (product.getId() == null) {
                product.setModifyOperatorId(credential.getOpId());
                product.setModifyTime(new Date());
                productService.save(product);
            } else {
                productService.update(product);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save产品异常", e);
                return failJsonResult("save产品异常");
            }

        }
    }
}
