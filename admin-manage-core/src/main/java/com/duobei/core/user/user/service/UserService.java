package com.duobei.core.user.user.service;

import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface UserService {
    /**
     * 根据用户ID获取用户详细信息
     * @param userId
     * @return
     */
    UserInfoVo getUserInfoById(Long userId);
}
