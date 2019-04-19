package com.duobei.core.user.user.dao;

import com.duobei.core.user.user.domain.vo.UserLoginLastLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/17
 */
public interface UserLoginLogDao {
    UserLoginLastLogVo getLastLoginByUserNameAndProduct(@Param("userName") String userNameMd5,@Param("productId") Integer productId);

    List<UserLoginLastLogVo> getLastLogin5ByUserNameAndProduct(@Param("userName") String userNameMd5,@Param("productId") Integer productId);
}
