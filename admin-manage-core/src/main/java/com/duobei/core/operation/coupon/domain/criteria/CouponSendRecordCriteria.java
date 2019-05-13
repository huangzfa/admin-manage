package com.duobei.core.operation.coupon.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
@Data
public class CouponSendRecordCriteria  extends Pagination {
    private Integer productId;

    private String couponName;

    private String sendStartTime;

    private String sendEndTime;

    private Integer state;
}
