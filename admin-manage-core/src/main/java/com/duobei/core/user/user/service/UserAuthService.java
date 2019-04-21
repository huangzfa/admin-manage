package com.duobei.core.user.user.service;

import com.duobei.common.exception.TqException;
import com.duobei.core.user.user.domain.vo.UserAuthListVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;

import java.util.Date;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface UserAuthService {

    void resetUserAuthByUserId(String authCode, Long userId) throws TqException;


    List<UserAuthListVo> getAuthListVoByUser(UserInfoVo userInfoVo) throws TqException;
}
