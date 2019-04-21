package com.duobei.core.user.user.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.user.user.domain.criteria.UserCriteria;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.domain.vo.UserListVo;

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

    ListVo<UserListVo> getListByQuery(UserCriteria userCriteria);
}
