package com.duobei.core.user.user.dao;

import com.duobei.core.user.user.domain.UserBankcard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface UserBankcardDao {
    List<UserBankcard> getByUserId(Long userId);

    void setNotMainBankByUserId(Long userId);

    int setMainBank(@Param("id") Long id, @Param("userId") Long userId);
}
