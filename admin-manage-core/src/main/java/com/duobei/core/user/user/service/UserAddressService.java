package com.duobei.core.user.user.service;

import com.duobei.core.user.user.domain.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/18
 */
public interface UserAddressService {
    /**
     * 根据用户获取收货地址
     * @param userId
     * @return
     */
    List<UserAddress> getByUserId(Long userId);
}
