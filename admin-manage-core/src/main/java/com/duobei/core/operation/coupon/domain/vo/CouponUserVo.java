package com.duobei.core.operation.coupon.domain.vo;

import com.duobei.core.operation.coupon.domain.CouponUser;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
@Data
public class CouponUserVo extends CouponUser {
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

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户手机号(加密后)
     */
    private String userNameEncrypt;

    private Long borrowId;


}
