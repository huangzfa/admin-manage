package com.duobei.core.user.user.dao;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public interface UserIdcardDao {
    /**
     * 根据姓名查询用户id
     * @param realName
     * @return
     */
    List<Long> getUserIdsByRealName(String realName);
}
