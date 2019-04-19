package com.duobei.core.user.user.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.core.user.user.dao.UserBankcardDao;
import com.duobei.core.user.user.dao.mapper.UserBankcardMapper;
import com.duobei.core.user.user.domain.UserBankcard;
import com.duobei.core.user.user.service.UserBankcardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
@Service("userBankcardService")
public class UserBankcardServiceImpl implements UserBankcardService {

    @Resource
    UserBankcardDao userBankcardDao;
    @Resource
    UserBankcardMapper userBankcardMapper;
    @Override
    public List<UserBankcard> getByUserId(Long userId) {
        return userBankcardDao.getByUserId(userId);
    }

    @Override
    public void setMainBank(Long id, Long userId) throws TqException {
        //先将该用户主卡置为副卡
        userBankcardDao.setNotMainBankByUserId(userId);
        //再将选中卡置为主卡
        int count = userBankcardDao.setMainBank(id,userId);
        if (count != 1){
            throw new TqException("设置主卡错误");
        }

    }
}
