package com.duobei.core.user.user.service.impl;

import com.duobei.core.user.user.dao.UserAddressDao;
import com.duobei.core.user.user.dao.mapper.UserAddressMapper;
import com.duobei.core.user.user.domain.UserAddress;
import com.duobei.core.user.user.service.UserAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/18
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {
    @Resource
    UserAddressDao userAddressDao;
    @Resource
    UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getByUserId(Long userId) {
        return userAddressDao.getByUserId(userId);
    }
}
