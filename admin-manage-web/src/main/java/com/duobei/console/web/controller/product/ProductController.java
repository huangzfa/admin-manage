package com.duobei.console.web.controller.product;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
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

/**
 * @author huangzhongfa
 * @description 产品管理
 * @date 2019/2/26
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/product")
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;
    @Autowired
    private MerchantService merchantService;

    /**
     * 产品列表页
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        model.addAttribute("merchantList",merchantService.getAll());
        return "product/list";
    }

    /**
     * ajax查询产品列表
     * @return
     */
    @RequiresPermissions("product:list:view")
    @RequestMapping(value = "/getProductData")
    @ResponseBody
    public String getProductData(ProductCriteria criteria){
        try {
            if( criteria == null ){
                criteria = new ProductCriteria();
            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<Product> list = productService.getLists(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("ajax查询产品列表异常",e);
                return failJsonResult("查询失败");
            }
        }
    }
}
