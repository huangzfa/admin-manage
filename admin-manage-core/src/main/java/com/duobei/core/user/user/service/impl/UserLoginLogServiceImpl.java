package com.duobei.core.user.user.service.impl;

import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.dao.UserLoginLogDao;
import com.duobei.core.user.user.domain.User;
import com.duobei.core.user.user.domain.UserLoginLog;
import com.duobei.core.user.user.domain.vo.UserLoginLastLogVo;
import com.duobei.core.user.user.service.UserLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/17
 */
@Service("userLoginLogService")
public class UserLoginLogServiceImpl implements UserLoginLogService {

    @Resource
    UserLoginLogDao userLoginLogDao;

    @Resource
    UserDao userDao;

    @Resource
    AppDao appDao;

    @Override
    public List<UserLoginLastLogVo> getByUserId(Long id) {
        User user = userDao.getById(id);
        //查询应用集合
        Map<Integer,App> appMap = appDao.getMapAll();
        //查询用户最近5次登录记录
        List<UserLoginLastLogVo> loginLastLogVos =  userLoginLogDao.getLastLogin5ByUserNameAndProduct(user.getUserNameMd5(),user.getProductId());
        for (UserLoginLastLogVo userLoginLastLogVo : loginLastLogVos){
            userLoginLastLogVo.setAppName(appMap.get(userLoginLastLogVo.getAppId()).getAppName());
        }
        return loginLastLogVos;
    }
}
