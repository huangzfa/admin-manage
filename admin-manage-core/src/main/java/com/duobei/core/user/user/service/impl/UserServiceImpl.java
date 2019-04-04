package com.duobei.core.user.user.service.impl;

import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.User;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.service.UserService;
import com.pgy.data.handler.PgyDataHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    ProductDao productDao;

    @Resource
    AppDao appDao;

    @Resource
    UserDao userDao;

    @Override
    public UserInfoVo getUserInfoById(Long userId) {
        //根据用户I的查询用户信息
        UserInfoVo userData = userDao.getUserInfoVoById(userId);
        //解密
        userData.setRealName(PgyDataHandler.decrypt(userData.getRealNameEncrypt()));
        userData.setUserName(PgyDataHandler.decrypt(userData.getUserNameEncrypt()));
        //根据用户的appId 和productId获取相关的应用和产品信息
        App appData = appDao.getById(userData.getAppId());
        Product productData = productDao.getById(userData.getProductId());
        //封装数据
        userData.setApp(appData);
        userData.setProduct(productData);
        return userData;
    }
}
