package com.duobei.core.operation.coupon.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.coupon.domain.CouponSendRecord;
import com.duobei.core.operation.coupon.domain.criteria.CouponSendRecordCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponSendRecordVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/10
 */
public interface CouponSendRecordService {
    /**
     * 分页查询优惠券
     * @param criteria
     * @return
     */
    ListVo<CouponSendRecordVo> getPage(CouponSendRecordCriteria criteria);
    /**
     *
     * @param phone
     * @param couponId
     * @param credential
     * @throws TqException
     */
    void sendCouponByPhone(String phone, Long couponId,  OperatorCredential credential) throws TqException;

    /**
     * 批量发送优惠券
     * @param path
     * @param listob
     * @param couponId
     * @param credential
     * @return
     * @throws TqException
     */
    HashMap<String, Object> batchSendCoupon(String path, List<List<Object>> listob, Long couponId, OperatorCredential credential) throws TqException;
}
