package com.duobei.core.operation.coupon.dao;

import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.domain.criteria.CouponCriteria;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/17
 */
public interface CouponDao {
    @MapKey("id")
    Map<Integer,Coupon> getMapByIds(@Param("ids") List<Integer> couponIds);

    List<Coupon> getCouponList(@Param("couponType") String couponType);

    Coupon getCouponById(@Param("couponId") Long couponId);

    List<Coupon> getPage(CouponCriteria criteria);

    int countByCriteria(CouponCriteria criteria);

    int update(Coupon record);

    int save(Coupon record);
}
