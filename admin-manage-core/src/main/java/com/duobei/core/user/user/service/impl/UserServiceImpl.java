package com.duobei.core.user.user.service.impl;

import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.channel.dao.PromotionChannelDao;
import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.dao.UserIdcardDao;
import com.duobei.core.user.user.dao.UserLoginLogDao;
import com.duobei.core.user.user.dao.mapper.UserMapper;
import com.duobei.core.user.user.domain.User;
import com.duobei.core.user.user.domain.UserAuth;
import com.duobei.core.user.user.domain.UserExample;
import com.duobei.core.user.user.domain.criteria.UserCriteria;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.domain.vo.UserListVo;
import com.duobei.core.user.user.domain.vo.UserLoginLastLogVo;
import com.duobei.core.user.user.service.UserLoginLogService;
import com.duobei.core.user.user.service.UserService;
import com.pgy.data.handler.PgyDataHandler;
import org.apache.regexp.RE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    UserIdcardDao userIdcardDao;

    @Resource
    UserMapper userMapper;

    @Resource
    UserLoginLogDao userLoginLogDao;

    @Resource
    PromotionChannelDao promotionChannelDao;

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
        //查询用户渠道信息
        PromotionChannel promotionChannelData = promotionChannelDao.getById(userData.getChannelId());
        //查询最后登录时间
        UserLoginLastLogVo lastLogVo = userLoginLogDao.getLastLoginByUserNameAndProduct(userData.getUserNameMd5(),userData.getProductId());
        //封装数据
        if (appData != null){
            userData.setApp(appData);
        }else{
            userData.setApp(new App());
        }
        if (productData != null){
            userData.setProduct(productData);
        }else{
            userData.setProduct(new Product());
        }
        if (promotionChannelData != null){
            userData.setPromotionChannel(promotionChannelData);
        }else{
            userData.setPromotionChannel(new PromotionChannel());
        }
        if (lastLogVo != null){
            userData.setLastLogVo(lastLogVo);
        }else{
            userData.setLastLogVo(new UserLoginLastLogVo());
        }
        return userData;
    }

    @Override
    public ListVo<UserListVo> getListByQuery(UserCriteria userCriteria) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0L);
        //获取用户可查询到的产品id 和map集合
        List<Integer> productIds = new ArrayList<>();
        Map<Integer,Product> productMap = new HashMap<>();
        for (Product product : userCriteria.getProductList()){
            productIds.add(product.getId());
            productMap.put(product.getId(),product);
        }
        criteria.andProductIdIn(productIds);
        //查询条件封装
        if (userCriteria.getUserId() != null){
            criteria.andIdEqualTo(userCriteria.getUserId());
        }
        if (StringUtil.isNotEmpty(userCriteria.getGlobalUserId())){
            criteria.andGlobalUserIdEqualTo(userCriteria.getGlobalUserId());
        }
        if (StringUtil.isNotEmpty(userCriteria.getUserName())){
            userCriteria.setUserName(PgyDataHandler.md5(userCriteria.getUserName()));
            criteria.andUserNameMd5EqualTo(userCriteria.getUserName());
        }
        if (StringUtil.isNotEmpty(userCriteria.getRealName())){
            List<Long> userIds = userIdcardDao.getUserIdsByRealName(PgyDataHandler.md5(userCriteria.getRealName()));
            userCriteria.setUserIds(userIds);
            criteria.andIdIn(userIds);
        }
        Long total = userMapper.countByExample(example);
        List<UserListVo> data = null;
        if (total > 0){
            data = userDao.getListByQuery(userCriteria);
        }
        if (data != null){
            List<String> userNameList = new ArrayList<>();
            for (UserListVo userListVo : data){
                userNameList.add(userListVo.getUserNameMd5());
            }
            //查询产品map（id,app）
            Map<Integer,App> appMap = appDao.getMapAll();
            //用户数据封装
            for(UserListVo userListVo : data){
                userListVo.setProductName(productMap.get(userListVo.getProductId()).getProductName());
                //查询登录信息
                UserLoginLastLogVo lastLogVo = userLoginLogDao.getLastLoginByUserNameAndProduct(userListVo.getUserNameMd5(),userListVo.getProductId());
                if (lastLogVo != null){
                    lastLogVo.setAppName(appMap.get(lastLogVo.getAppId()).getAppName());
                    userListVo.setLastLogVo(lastLogVo);
                }else{
                    userListVo.setLastLogVo(new UserLoginLastLogVo());
                }
            }
        }
        return new ListVo(total.intValue(),data);
    }

    /**
     * 逾期短信推送名单
     * @param userIdList
     * @return
     */
    @Override
    public List<User> getByListId(List<Long> userIdList){
        return userDao.getByListId(userIdList);
    }

}
