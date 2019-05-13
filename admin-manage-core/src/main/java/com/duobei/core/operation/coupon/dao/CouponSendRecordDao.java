package com.duobei.core.operation.coupon.dao;

import com.duobei.core.operation.coupon.domain.criteria.CouponSendRecordCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponSendRecordVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/10
 */
public interface CouponSendRecordDao {
    List<CouponSendRecordVo> getPage(CouponSendRecordCriteria criteria);

    int countByCriteria(CouponSendRecordCriteria criteria);

}
