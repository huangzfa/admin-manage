package com.duobei.core.operation.coupon.service.impl;

import com.duobei.core.operation.coupon.dao.CouponDao;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/28
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    /**
     * 根据优惠券类型查询优惠券
     * @param couponType
     * @return
     */
    @Override
    public List<Coupon> getCouponList(String couponType){
        return couponDao.getCouponList(couponType);
    }

    /**
     *
     * @param couponId
     * @return
     */
    @Override
    public Coupon getCouponById(Long couponId){
        return couponDao.getCouponById(couponId);
    }
}
