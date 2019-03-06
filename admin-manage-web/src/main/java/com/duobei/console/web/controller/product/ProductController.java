package com.duobei.console.web.controller.product;

import com.alibaba.fastjson.JSON;
import com.duobei.common.enums.BusinessEnum;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.consume.service.ConsumeLoanConfigService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.ProductBusiness;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.BusinessVo;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import com.duobei.core.operation.product.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ConsumeLoanConfigService consumeLoanConfigService;
    @Autowired
    private ProductAuthConfigService productAuthConfigService;
    @Autowired
    private ProductBusinessService productBusinessService;
    @Autowired
    private BusinessService businessService;

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
        List<ProductBusiness> list = new ArrayList<>();
        if (!StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            if( product!=null ){
                list = productBusinessService.getByProductId(product.getId());
            }
        }
        model.addAttribute("merchants",merchantService.getAll());
        List<BusinessVo> listBusin =  businessService.getAll();
        for(BusinessVo vo : listBusin){
            for( ProductBusiness business : list){
                if(vo.getBizCode().equals(business.getBizCode())){
                    vo.setChecked("checked");
                }
            }
        }
        model.addAttribute("listBusin",listBusin);
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
        if (!StringUtil.isBlank(productCode)) {
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
        model.addAttribute("productCode",productCode);
        Product product = productService.getByCode(productCode);
        //目前只针对消费贷业务
        String show = "false";
        if( product !=null ){
            List<ProductBusiness> list = productBusinessService.getByProductId(product.getId());
            for(ProductBusiness business : list){
                if(business.getBizCode().equals(BusinessEnum.XFD.getCode())){
                    show = "true";
                    break;
                }
            }
        }
        model.addAttribute("show",show);
        return "product/tabs";
    }

    /**
     * 产品表单添加页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/auth")
    public String auth(Model model, String productCode) {
        if (!StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            if( product !=null ){
                model.addAttribute("consumeLoanConfig",JSON.toJSONString(consumeLoanConfigService.getByProductId(product.getId())));
                model.addAttribute("authConfigs",JSON.toJSONString(productAuthConfigService.getByProductId(product.getId())));
            }
        }
        return "product/pAuthConfig";
    }

    /**
     *
     * @param model
     * @param productCode
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/loan")
    public String loan(Model model, String productCode) {
        if (!StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            if( product !=null ){
                model.addAttribute("consumeLoanConfig",JSON.toJSONString(consumeLoanConfigService.getByProductId(product.getId())));
                model.addAttribute("authConfigs",JSON.toJSONString(productAuthConfigService.getByProductId(product.getId())));
            }
        }
        return "product/pLoanConfig";
    }

    /**
     *
     * @param model
     * @param productCode
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/rateDay")
    public String rateDay(Model model, String productCode) {
        if (!StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            if( product !=null ){
                model.addAttribute("consumeLoanConfig",JSON.toJSONString(consumeLoanConfigService.getByProductId(product.getId())));
                model.addAttribute("authConfigs",JSON.toJSONString(productAuthConfigService.getByProductId(product.getId())));
            }
        }
        return "product/pRateDayConfig";
    }
    /**
     * 产品保存修改操作
     *
     * @param request
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/auth/save")
    @ResponseBody
    public String save(HttpServletRequest request) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            String loans = request.getParameter("loan");
            if( StringUtil.isBlank(loans)){
                throw new TqException("请填写借贷基本配置");
            }
            String auths = request.getParameter("auths");
            if( StringUtil.isBlank(auths)){
                throw new TqException("至少关联一个认证项配置");
            }
            ConsumeLoanConfig loan = JSON.parseObject(loans,ConsumeLoanConfig.class);
            if( loan.getShowMinAmount() >= loan.getShowMaxAmount()){
                throw new TqException("借款额度范围，第一个值必须小于第二个值");
            }
            if( loan.getShowMinAmount() < 100 || loan.getShowMinAmount() % 100 >0 || loan.getShowMaxAmount() % 100 >0){
                throw new TqException("借款额度范围，请填写100的整数倍");
            }
            List<ProductAuthConfigVo> auth = JSON.parseArray(auths,ProductAuthConfigVo.class);
            if( auth.size() == 0){
                throw new TqException("至少关联一个认证项配置");
            }
            if( loan.getId() == null ){
                loan.setAddOperatorId(credential.getOpId());
            }
            loan.setModifyTime(new Date());
            loan.setModifyOperatorId(credential.getOpId());
            consumeLoanConfigService.saveAuth(loan,auth);
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

    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/mpSave")
    @ResponseBody
    public String mpSave(HttpServletRequest request,ProductVo productVo) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( productVo.getId() == null ){
                productVo.setAddOperatorId(credential.getOpId());
                productService.saveMp(productVo);
            }else{
                productVo.setModifyTime(new Date());
                productVo.setModifyOperatorId(credential.getOpId());
                productService.updateMp(productVo);
            }
            return simpleSuccessJsonResult("success");
        }catch (Exception e){
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save产品异常", e);
                return failJsonResult("save产品异常");
            }
        }
    }
}
