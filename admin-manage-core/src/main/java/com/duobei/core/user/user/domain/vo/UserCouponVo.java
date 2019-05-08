package com.duobei.core.user.user.domain.vo;

import com.duobei.core.user.user.domain.UserCoupon;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
@Data
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

    /**
     * 订单编号
     */
    private String borrowNo;


}
