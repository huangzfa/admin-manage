package com.duobei.console.web.controller.market;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.operation.coupon.domain.criteria.CouponUserCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponUserVo;
import com.duobei.core.operation.coupon.service.CouponUserService;
import com.duobei.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/7
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/market/couponUser")
public class CouponUserController extends BaseController {

    @Autowired
    private CouponUserService couponUserService;

    /**
     * 优惠券列表页
     * @param model
     * @return
     */
    @RequiresPermissions("market:couponUser:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "market/userCoupon/list";
    }

    /**
     * ajax查询优惠券列表
     * @return
     */
    @RequiresPermissions("market:couponUser:view")
    @RequestMapping(value = "/getCouponData")
    @ResponseBody
    public String getProductData(CouponUserCriteria criteria){
        try {
            //验证数据权限
            validAuthData(criteria.getProductId());
            if( criteria == null ){
                criteria = new CouponUserCriteria();
            }
            if (criteria.getPagesize()== BizConstant.INT_ZERO) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            if( !StringUtil.isEmpty(criteria.getUseStartTime()) && !StringUtil.isEmpty(criteria.getUseEndTime()) ){
                Date date1 = DateUtil.valueOf(criteria.getUseStartTime());
                Date date2 = DateUtil.valueOf(criteria.getUseEndTime());
                if(date1.getTime() > date2.getTime()){
                    throw new TqException("开始时间不能大于结束时间");
                }
            }
            if( !StringUtil.isEmpty(criteria.getReceiveStartTime()) && !StringUtil.isEmpty(criteria.getReceiveEndTime()) ){
                Date date1 = DateUtil.valueOf(criteria.getReceiveStartTime());
                Date date2 = DateUtil.valueOf(criteria.getReceiveEndTime());
                if(date1.getTime() > date2.getTime()){
                    throw new TqException("开始时间不能大于结束时间");
                }
            }
            ListVo<CouponUserVo> list = couponUserService.getPage(criteria);
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
}
