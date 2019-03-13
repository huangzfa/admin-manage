package com.duobei.core.user.user.dao;

import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import java.util.List;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface UserDao {

    UserAndIdCardVo getUserByMobileAndProductId(@Param("mobile") String mobile, @Param("productId") Integer productId);
    @MapKey("id")
    Map<Long,UserAndIdCardVo> getUserAndIdCardMapByBorrowUserIds(@Param("borrowData") List<BorrowCashListVo> data);

    UserInfoVo getUserInfoVoById(Long userId);
}
