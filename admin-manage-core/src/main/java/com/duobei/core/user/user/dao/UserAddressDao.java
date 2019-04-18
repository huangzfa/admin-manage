package com.duobei.core.user.user.dao;

import com.duobei.core.user.user.domain.UserAddress;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/18
 */
public interface UserAddressDao {
    List<UserAddress> getByUserId(Long userId);
}
