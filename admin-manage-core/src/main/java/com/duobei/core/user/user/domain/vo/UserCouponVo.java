package com.duobei.core.user.user.domain.vo;

import com.duobei.core.user.user.domain.UserCoupon;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public class UserCouponVo extends UserCoupon {
    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 面值
     */
    private BigDecimal amount;

    /**
     * 优惠券类型
     */
    private String couponTypeName;

    public String getCouponTypeName() {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
