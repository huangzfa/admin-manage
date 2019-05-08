package com.duobei.core.user.user.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/7
 */
@Data
public class UserCouponCriteria extends Pagination {
    private String borrowNo;//订单编号

    private String userNo;//用户编号

    private String receiveStartTime;//领取开始时间

    private String receiveEndTime;//领取结束时间

    private String useStartTime;//使用开始时间

    private String useEndTime;//使用结束时间

    private String couponName;//优惠券名称
}
