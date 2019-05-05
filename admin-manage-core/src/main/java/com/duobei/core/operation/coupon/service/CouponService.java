package com.duobei.core.operation.coupon.service;

import com.duobei.core.operation.coupon.domain.Coupon;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/28
 */
public interface CouponService {
    /**
     * 根据优惠券类型查询优惠券
     * @param couponType
     * @return
     */
    List<Coupon> getCouponList(String couponType);

    /**
     *
     * @param couponId
     * @return
     */
    Coupon getCouponById(Long couponId);
}
