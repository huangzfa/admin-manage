package com.duobei.core.operation.coupon.dao;

import com.duobei.core.operation.coupon.domain.CouponUser;
import com.duobei.core.operation.coupon.domain.criteria.CouponUserCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface CouponUserDao {
    List<CouponUserVo> getByUserIdAndState(@Param("userId") Long id, @Param("state") Integer state, @Param("orderName") String orderName);

    List<CouponUserVo> getPage(CouponUserCriteria criteria);

    int countByCriteria(CouponUserCriteria criteria);

    int save(CouponUser record);

    void batchSave(@Param("temp") List<CouponUser> temp);

    /**
     * 查询用户领过多少优惠券
     * @param userId
     * @param couponId
     * @return
     */
    int countByUserId(@Param("userId") Long userId,@Param("couponId") Long couponId,@Param("productId") Integer productId);

    /**
     * 查看用户领了这个优惠券次数
     * @param userIds
     * @return
     */
    List<Map<String,Object>> getCountGroupUserId(@Param("productId") Integer productId, @Param("couponId") Long couponId,@Param("userIds") List<Long> userIds);


}
