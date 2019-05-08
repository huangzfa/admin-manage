package com.duobei.core.operation.coupon.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.domain.criteria.CouponCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/28
 */
public interface CouponService {

    /**
     * 分页查询优惠券
     * @param criteria
     * @return
     */
    ListVo<Coupon> getPage(CouponCriteria criteria);

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

    /**
     * 保存优惠券
     * @param record
     * @throws TqException
     */
    void save(Coupon record) throws TqException;

    /**
     * 修改优惠券
     * @param record
     * @throws TqException
     */
    void update(Coupon record) throws TqException;
}
