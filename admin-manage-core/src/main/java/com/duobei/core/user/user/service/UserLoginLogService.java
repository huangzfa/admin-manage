package com.duobei.core.user.user.service;

import com.duobei.core.user.user.domain.UserLoginLog;
import com.duobei.core.user.user.domain.vo.UserLoginLastLogVo;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/17
 */
public interface UserLoginLogService {

    List<UserLoginLastLogVo> getByUserId(Long id);
}
