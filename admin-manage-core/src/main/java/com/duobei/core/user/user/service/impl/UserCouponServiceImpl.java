package com.duobei.core.user.user.service.impl;

import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.coupon.dao.CouponDao;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.user.user.dao.UserCouponDao;
import com.duobei.core.user.user.dao.mapper.UserCouponMapper;
import com.duobei.core.user.user.domain.vo.UserCouponVo;
import com.duobei.core.user.user.service.UserCouponService;
import com.duobei.dic.ZD;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
@Service("userCouponService")
public class UserCouponServiceImpl implements UserCouponService {
    @Resource
    UserCouponDao userCouponDao;
    @Resource
    UserCouponMapper userCouponMapper;
    @Resource
    CouponDao couponDao;
    @Override
    public List<UserCouponVo> getByUserIdAndState(Long id, Integer state) {
        String orderName;
        if (state == ZD.couponState_new){
            orderName = "add_time";
        }else if(state == ZD.couponState_used){
            orderName = "used_time";
        }else if(state == ZD.couponState_expire){
            orderName = "end_time";
        }else{
            orderName = null;
        }
        List<UserCouponVo> list = userCouponDao.getByUserIdAndState(id,state,orderName);
        List<Integer> couponIds = new ArrayList<>();
        //赋值优惠券类型名称，获取优惠券ids
        for (UserCouponVo userCouponVo : list){
            userCouponVo.setCouponTypeName(DictUtil.getDictLabel(ZD.couponType,userCouponVo.getCouponType()));
            couponIds.add(userCouponVo.getCouponId());
        }
        //获取优惠券id,优惠券类型的map集合
        Map<Integer,Coupon> couponMap = couponDao.getMapByIds(couponIds);
        //赋值优惠券名称
        for (UserCouponVo userCouponVo : list){
            Coupon coupon = couponMap.get(userCouponVo);
            userCouponVo.setCouponName(coupon.getCouponName());
        }
        return list;
    }
}
