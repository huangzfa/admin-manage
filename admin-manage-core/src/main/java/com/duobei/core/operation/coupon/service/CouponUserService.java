package com.duobei.core.operation.coupon.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.coupon.domain.CouponUser;
import com.duobei.core.operation.coupon.domain.criteria.CouponUserCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponUserVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface CouponUserService {
    List<CouponUserVo> getByUserIdAndState(Long id, Integer couponStateUsed);

    /**
     * 分页查询优惠券
     * @param criteria
     * @return
     */
    ListVo<CouponUserVo> getPage(CouponUserCriteria criteria);


    /**
     * 批量写入优惠券
     * @param temp
     */
    void batchSave(List<CouponUser> temp);
}
