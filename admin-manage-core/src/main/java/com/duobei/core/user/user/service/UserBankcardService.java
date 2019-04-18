package com.duobei.core.user.user.service;

import com.duobei.common.exception.TqException;
import com.duobei.core.user.user.domain.UserBankcard;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/12
 */
public interface UserBankcardService {
    /**
     * 查询用户银行卡信息
     * @param userId
     * @return
     */
    List<UserBankcard> getByUserId(Long userId);

    /**
     * 设置主卡
     * @param id
     * @param userId
     */
    void setMainBank(Long id, Long userId) throws TqException;
}
