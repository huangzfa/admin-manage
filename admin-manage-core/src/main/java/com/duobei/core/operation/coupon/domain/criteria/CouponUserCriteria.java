package com.duobei.core.operation.coupon.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/7
 */
@Data
public class CouponUserCriteria extends Pagination {
    private String orderNo;//订单编号

    private String phone;//UserCouponVo用户编号(手机号)

    private Long userId;//用户id

    private Long couponId;//优惠券id

    private String couponIds;//优惠券ids

    private String receiveStartTime;//领取开始时间

    private String receiveEndTime;//领取结束时间

    private String useStartTime;//使用开始时间

    private String useEndTime;//使用结束时间

    private String couponName;//优惠券名称

    private Integer productId;

    private Integer state;//劵状态 【 0：未使用，1：已使用，2：冻结，3：过期 】
}
