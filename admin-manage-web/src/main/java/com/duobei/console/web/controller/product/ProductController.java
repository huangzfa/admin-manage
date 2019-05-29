package com.duobei.console.web.controller.product;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.BusinessEnum;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.NumberUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfig;
import com.duobei.core.operation.consume.domain.ConsumeLoanRenewalConfig;
import com.duobei.core.operation.consume.domain.vo.ConsumeLoanRenewalConfigVo;
import com.duobei.core.operation.consume.service.ConsumeLoanConfigService;
import com.duobei.core.operation.consume.service.ConsumeLoanRateDayConfigService;
import com.duobei.core.operation.consume.service.ConsumeLoanRenewalConfigService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.ProductBusiness;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.BusinessVo;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;
import com.duobei.core.operation.product.domain.vo.ProductConsumdebtGoodsVo;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import com.duobei.core.operation.product.service.*;
import com.duobei.utils.RiskUtil;
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
    @Autowired
    private ProductConsumdebtGoodsService productConsumdebtGoodsService;
    @Autowired
    private ConsumeLoanRateDayConfigService rateDayConfigService;
    @Autowired
    private ConsumeLoanRenewalConfigService renewalConfigService;
    @Autowired
    private RiskUtil riskUtil;

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
     * 商户产品表单添加页
     * @param model
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/mpForm")
    public String mpForm(Model model, String productCode) {
        List<ProductBusiness> list = new ArrayList<>();
        //根据产品code查询产品
        if (!StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            //产品不为空，查询产品关联的业务
            if( product!=null ){
                list = productBusinessService.getByProductId(product.getId());
            }
        }
        model.addAttribute("merchants",merchantService.getAll());
        //查询所有业务类型，和产品关联的业务，已经关联的checked处理
        ListVo<BusinessVo> listBusin =  businessService.getPageList();
        for(BusinessVo vo : listBusin.getRows()){
            for( ProductBusiness business : list){
                if(vo.getBizCode().equals(business.getBizCode())){
                    vo.setChecked("checked");
                    continue;
                }
            }
        }
        model.addAttribute("listBusin",listBusin.getRows());
        return "product/mpForm";
    }

    /**
     * 产品详情添加页
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
     * 消费贷产品配置
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
                    show = "true";//代表显示这一个模块
                    break;
                }
            }
        }
        model.addAttribute("show",show);
        return "product/tabs";
    }

    /**
     * 消费贷- 基础借款配置
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
                //查询消费贷基础配置
                ConsumeLoanConfig config = consumeLoanConfigService.getByProductId(product.getId());
                if( config == null ){
                    config = new ConsumeLoanConfig();
                }
                config.setDayAmountLimit(config.getDayAmountLimit()==null?null:config.getDayAmountLimit()/100);
                config.setShowMinAmount(config.getDayAmountLimit()==null?null:config.getShowMinAmount()/100);
                config.setShowMaxAmount(config.getShowMaxAmount()==null?null:config.getShowMaxAmount()/100);
                config.setOverdueRate(config.getOverdueRate());
                config.setPoundageRate(config.getPoundageRate());
                config.setProductId(product.getId());
                model.addAttribute("consumeLoanConfig",JSON.toJSONString(config));
                //查询消费贷基础配置，关联的认证项
                model.addAttribute("authConfigs",JSON.toJSONString(productAuthConfigService.getByProductId(product.getId())));
            }
        }
        return "product/pAuthConfig";
    }

    /**
     *消费贷-消费贷相关配置
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
                //查询消费贷基础配置
                ConsumeLoanConfig record = consumeLoanConfigService.getByProductId(product.getId());
                ConsumeLoanConfig config = new ConsumeLoanConfig();
                config.setProductId(product.getId());
                //因为这一页只显示3项数据，所以没必要全部返回，返回record就好
                if( record != null ){
                    config.setId(record.getId());
                    config.setQuotaSceneCode(record.getQuotaSceneCode());
                    config.setBorrowSceneCode(record.getBorrowSceneCode());
                    config.setBorrowSceneCodeFirst(record.getBorrowSceneCodeFirst());
                    config.setDataVersion(record.getDataVersion());
                    config.setProductId(record.getProductId());
                }
                model.addAttribute("consumeLoanConfig",JSON.toJSONString(config));
                //返回消费贷关联的是商品
                model.addAttribute("productGoods",JSON.toJSONString(productConsumdebtGoodsService.getByProductId(product.getId())));
            }
        }
        return "product/pLoanConfig";
    }

    /**
     *消费贷-期限利率配置
     * @param model
     * @param productCode
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/rateDay")
    public String rateDay(Model model, String productCode) {
        ConsumeLoanConfig config = null;
        if (!StringUtil.isBlank(productCode)) {
            Product product = productService.getByCode(productCode);
            model.addAttribute("product", product);
            if( product !=null ){
                //查询消费贷基础配置
                ConsumeLoanConfig record = consumeLoanConfigService.getByProductId(product.getId());
                config = new ConsumeLoanConfig();
                config.setProductId(product.getId());
                //因为这一页只显示3项数据，所以没必要全部返回，返回record就好
                if( record != null ){
                    config.setId(record.getId());
                    config.setRenewalDay(record.getRenewalDay());
                    config.setCanRenewalDayLimit(record.getCanRenewalDayLimit());
                    config.setRenewalAmount(record.getRenewalAmount()/100);
                    config.setDataVersion(record.getDataVersion());

                }

            }
        }
        if( config == null){
            config = new ConsumeLoanConfig();
        }
        model.addAttribute("consumeLoanConfig",JSON.toJSONString(config));
        model.addAttribute("rateDays",JSON.toJSONString(rateDayConfigService.getByLoanConfigId(config.getId())));
        model.addAttribute("renewalConfigs",JSON.toJSONString(renewalConfigService.getByConfigId(config.getId())));
        return "product/pRateDayConfig";
    }
    /**
     * 基础借款配置保存
     *
     * @param request
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/auth/save")
    @ResponseBody
    public String authSave(HttpServletRequest request)  {
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
            if( loan.getShowMinAmount() < 100 || loan.getShowMinAmount() % 100 >BizConstant.INT_ZERO || loan.getShowMaxAmount() % 100 >BizConstant.INT_ZERO){
                throw new TqException("借款额度范围，请填写100的整数倍");
            }
            loan.setDayAmountLimit(loan.getDayAmountLimit()*100);//分单位
            loan.setShowMinAmount(loan.getShowMinAmount()*100);//分单位
            loan.setShowMaxAmount(loan.getShowMaxAmount()*100);//分单位
            List<ProductAuthConfigVo> auth = JSON.parseArray(auths,ProductAuthConfigVo.class);
            if( auth.size() == BizConstant.INT_ZERO){
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

    /**
     * 消费贷期限利率配置保存
     * @param request
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/rateDay/save")
    @ResponseBody
    public String rateDaySave(HttpServletRequest request) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            String loans = request.getParameter("loan");
            if( StringUtil.isBlank(loans)){
                throw new TqException("请填写借贷基本配置");
            }
            ConsumeLoanConfig loan = JSON.parseObject(loans,ConsumeLoanConfig.class);
            if( loan.getRenewalAmount() < 100 || loan.getRenewalAmount() % 100 >BizConstant.INT_ZERO ){
                throw new TqException("续借金额请填写100的整数倍");
            }
            loan.setRenewalAmount(loan.getRenewalAmount()*100);//分单位
            String rateDays = request.getParameter("rateDays");
            if( rateDays == null || rateDays.length() == 0){
                throw new TqException("系统错误");
            }
            List<ConsumeLoanRateDayConfig> rateDayList = JSON.parseArray(rateDays,ConsumeLoanRateDayConfig.class);
            String renewalConfigs = request.getParameter("renewalConfigs");
            if( renewalConfigs == null || renewalConfigs.length() == 0){
                throw new TqException("系统错误");
            }
            List<ConsumeLoanRenewalConfig> renewalsList = JSON.parseArray(renewalConfigs,ConsumeLoanRenewalConfig.class);
            if( loan.getId() == null ){
                loan.setAddOperatorId(credential.getOpId());
            }
            loan.setModifyTime(new Date());
            loan.setModifyOperatorId(credential.getOpId());
            consumeLoanConfigService.rateDaySave(loan,rateDayList,renewalsList);
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

    /**
     * 消费贷相关配置保存
     * @param request
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/loan/save")
    @ResponseBody
    public String loanSave(HttpServletRequest request) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            String loans = request.getParameter("loan");
            if( StringUtil.isBlank(loans)){
                throw new TqException("请填写借贷基本配置");
            }
            ConsumeLoanConfig loan = JSON.parseObject(loans,ConsumeLoanConfig.class);
            String goods = request.getParameter("goods");
            if( goods == null || goods.length() == BizConstant.INT_ZERO){
                throw new TqException("至少关联一个借贷商品");
            }
            List<ProductConsumdebtGoodsVo> goodsList = JSON.parseArray(goods,ProductConsumdebtGoodsVo.class);
            if( loan.getId() == null ){
                loan.setAddOperatorId(credential.getOpId());
            }
            loan.setModifyTime(new Date());
            loan.setModifyOperatorId(credential.getOpId());
            consumeLoanConfigService.saveLoan(loan,goodsList);
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

    /**
     * 产品保存
     * @param request
     * @param productVo
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/mpSave")
    @ResponseBody
    public String mpSave(HttpServletRequest request,ProductVo productVo){
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

    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request,ProductVo product) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( product.getId() == null ){
                product.setAddOperatorId(credential.getOpId());
                productService.save(product);
            }else{
                product.setModifyTime(new Date());
                product.setModifyOperatorId(credential.getOpId());
                productService.update(product);
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

    /**
     * 校验场景id
     * @param code
     * @return
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/config/validSceneCode")
    @ResponseBody
    public String validSceneCode(String code,Integer productId) throws TqException {
        if (StringUtil.isBlank(code)) {
            return failJsonResult("请填写场景id");
        }
        Product product = productService.getById(productId);
        if( product == null ){
            return failJsonResult("产品不存在");
        }
        String result = riskUtil.SceneCodeHad(code,productId,product.getMerchantId());
        if ( !result.equals("success")) {
            return failJsonResult("校验失败，原因：" + result);
        } else {
            return simpleSuccessJsonResult(result);
        }
    }

    /**
     * 产品删除
     * @param productCode
     * @param productState
     * @return
     * @throws TqException
     */
    @RequiresPermissions("product:list:edit")
    @RequestMapping(value = "/editState")
    @ResponseBody
    public String editState(String productCode,Integer productState) throws TqException{
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( StringUtil.isEmpty(productCode) || StringUtil.isEmpty(productState) ){
                throw new TqException("参数为空");
            }
            Product product = productService.getByCode(productCode);
            if( product == null){
                throw new TqException("产品不存在");
            }
            ProductVo vo = new ProductVo();
            vo.setId(product.getId());
            vo.setState(productState.byteValue());
            vo.setModifyTime(new Date());
            vo.setModifyOperatorId(credential.getOpId());
            productService.editState(vo);
            return simpleSuccessJsonResult("success");

        }catch (Exception e){
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("editState产品失败", e);
                return failJsonResult("修改失败");
            }

        }
    }
}
