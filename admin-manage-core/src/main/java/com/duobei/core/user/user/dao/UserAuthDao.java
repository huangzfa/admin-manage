package com.duobei.core.user.user.dao;

import com.duobei.core.user.user.domain.UserAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface UserAuthDao {
    UserAuth getByUserId(Long userId);

    int resetState(@Param("authCode") String authCode,@Param("userId") Long userId);

}
