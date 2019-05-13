package com.duobei.core.user.user.dao;

import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalListVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import com.duobei.core.user.user.domain.User;
import com.duobei.core.user.user.domain.UserExample;
import com.duobei.core.user.user.domain.criteria.UserCriteria;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import java.util.List;

import com.duobei.core.user.user.domain.vo.UserListVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface UserDao {

    UserAndIdCardVo getUserByMobileAndProductId(@Param("userName") String userName, @Param("productId") Integer productId);

    @MapKey("id")
    Map<Long,UserAndIdCardVo> getUserAndIdCardMapByBorrowUserIds(@Param("borrowData") List<BorrowCashListVo> data);


    @MapKey("id")
    Map<Long,UserAndIdCardVo> getUserAndIdCardMapByRepaymentUserIds(@Param("repaymentData") List<BorrowCashRepaymentListVo> data);

    UserInfoVo getUserInfoVoById(Long userId);

    @MapKey("id")
    Map<Long,UserAndIdCardVo> getUserAndIdCardMapByRenelwalUserIds(@Param("renewalData") List<BorrowCashRenewalListVo> data);

    List<UserListVo> getListByQuery(UserCriteria criteria);

    User getById(Long id);

    /**
     *
     * @return
     */
    User getByUserNameEncrypt(@Param("userNameEncrypt") String userNameEncrypt,@Param("productId") Integer productId);

    List<User> getByIds(@Param("productId") Integer productId,@Param("userIds") List<Long> userIds);
}
