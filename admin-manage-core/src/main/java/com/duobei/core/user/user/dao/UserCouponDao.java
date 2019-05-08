package com.duobei.core.user.user.dao;

import com.duobei.core.user.user.domain.criteria.UserCouponCriteria;
import com.duobei.core.user.user.domain.vo.UserCouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface UserCouponDao {
    List<UserCouponVo> getByUserIdAndState(@Param("userId") Long id, @Param("state") Integer state,@Param("orderName") String orderName);

    List<UserCouponVo> getPage(UserCouponCriteria criteria);

    int countByCriteria(UserCouponCriteria criteria);

}
