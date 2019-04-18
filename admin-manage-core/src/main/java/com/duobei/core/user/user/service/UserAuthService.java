package com.duobei.core.user.user.service;

import com.duobei.core.user.user.domain.vo.UserAuthListVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface UserAuthService {
    List<UserAuthListVo> getAuthListVoByUserId(Long id);

    void resetUserAuthByUserId(String authCode, Long userId);
}
