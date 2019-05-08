package com.duobei.core.user.user.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.user.user.domain.criteria.UserCouponCriteria;
import com.duobei.core.user.user.domain.vo.UserCouponVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface UserCouponService {
    List<UserCouponVo> getByUserIdAndState(Long id, Integer couponStateUsed);

    /**
     * 分页查询优惠券
     * @param criteria
     * @return
     */
    ListVo<UserCouponVo> getPage(UserCouponCriteria criteria);
}
