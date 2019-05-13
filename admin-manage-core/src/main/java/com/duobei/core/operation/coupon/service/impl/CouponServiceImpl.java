package com.duobei.core.operation.coupon.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.coupon.dao.CouponDao;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.domain.criteria.CouponCriteria;
import com.duobei.core.operation.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/28
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;


    @Override
    public ListVo<Coupon> getPage(CouponCriteria criteria) {
        int total = couponDao.countByCriteria(criteria);
        List<Coupon> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = couponDao.getPage(criteria);
        }
        return new ListVo<Coupon>(total, list);
    }

    /**
     * 根据优惠券类型查询优惠券
     * @param couponType
     * @return
     */
    @Override
    public List<Coupon> getCouponList(String couponType){
        return couponDao.getCouponList(couponType);
    }

    @Override
    public List<Coupon> getByProductId(Integer productId){
        return couponDao.getByProductId(productId);
    }
    /**
     *
     * @param couponId
     * @return
     */
    @Override
    public Coupon getCouponById(Long couponId){
        return couponDao.getCouponById(couponId);
    }


    @Override
    public void save(Coupon entity) throws TqException {
        if (couponDao.save(entity) != 1) {
            throw new TqException("优惠券保存失败");
        }
    }

    @Override
    public void update(Coupon record) throws TqException {
        if (couponDao.update(record) != 1) {
            throw new RuntimeException("优惠券修改失败");
        }
    }
}
