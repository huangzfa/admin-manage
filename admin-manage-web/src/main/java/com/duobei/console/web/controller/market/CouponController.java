package com.duobei.console.web.controller.market;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.domain.criteria.CouponCriteria;
import com.duobei.core.operation.coupon.service.CouponService;
import com.duobei.dic.ZD;
import com.duobei.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huangzhongfa
 * @description 优惠券管理
 * @date 2019/5/7
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/market/coupon")
public class CouponController extends BaseController {

    @Resource
    private CouponService couponService;

    /**
     * 优惠券列表页
     * @param model
     * @return
     */
    @RequiresPermissions("market:coupon:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        model.addAttribute("couponType", JSON.toJSONString(DictUtil.getDictList(ZD.couponType)));
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "market/coupon/list";
    }

    /**
     * ajax查询优惠券列表
     * @return
     */
    @RequiresPermissions("market:coupon:view")
    @RequestMapping(value = "/getCouponData")
    @ResponseBody
    public String getProductData(CouponCriteria criteria){
        try {
            //验证数据权限
            validAuthData(criteria.getProductId());
            if( criteria == null ){
                criteria = new CouponCriteria();
            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<Coupon> list = couponService.getPage(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("查询优惠券列表异常",e);
                return failJsonResult("查询失败");
            }
        }
    }

    /**
     * 跳转编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions("market:coupon:edit")
    @RequestMapping(value = "/form")
    public String form( Model model,Long id,Integer productId) throws TqException {
        model.addAttribute("productId", productId);
        Coupon coupon = null;
        if ( id !=null ) {
            coupon = couponService.getCouponById(id);
            coupon.setAmount(coupon.getAmount()/100);
            model.addAttribute("coupon", coupon);
            model.addAttribute("gmtEnd", DateUtil.formatWithDateTimeShort(coupon.getGmtEnd()));
            model.addAttribute("gmtStart", DateUtil.formatWithDateTimeShort(coupon.getGmtStart()));

        }else{
            model.addAttribute("coupon", new Coupon());
        }
        model.addAttribute("couponType", DictUtil.getDictList(ZD.couponType));
        return "market/coupon/form";
    }

    /**
     * 保存或修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "market:coupon:edit" })
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(Coupon entity) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( entity.getProductId() == null ){
                throw new TqException("产品id不能为空");
            }
            if( entity.getExpiryType().equals(BizConstant.INT_ONE)){
                if(DateUtil.compareTime(entity.getGmtStart(),entity.getGmtEnd(), Calendar.SECOND) > BizConstant.INT_ZERO){
                    throw new TqException("开始时间不能大于结束时间");
                }
                entity.setValidDays(BizConstant.MINUS_ONE);
            }
            entity.setAmount(entity.getAmount()*100);
            if (entity.getId() == null) {
                entity.setAddOperatorId(credential.getOpId());
                entity.setModifyOperatorId(credential.getOpId());
                couponService.save(entity);
            } else {
                entity.setModifyTime(new Date());
                entity.setModifyOperatorId(credential.getOpId());
                couponService.update(entity);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("save优惠券异常", e);
                return failJsonResult("save优惠券异常");
            }
        }
    }

    /**
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getByProductId")
    @ResponseBody
    public String getByProductId(Integer productId) {
        return successJsonResult("success", "list", couponService.getByProductId(productId));
    }
}
