package com.duobei.core.operation.coupon.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

@Data
public class CouponCriteria extends Pagination {
    private String couponType;//优惠券类型

    private String couponName;//优惠券名称

    private Integer productId;//产品id

    private Integer state;//使用状态



}
