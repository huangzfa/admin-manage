package com.duobei.core.user.user.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.coupon.dao.CouponDao;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.transaction.borrow.dao.BorrowCashDao;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.repayment.dao.BorrowCashRepaymentDao;
import com.duobei.core.user.user.dao.UserCouponDao;
import com.duobei.core.user.user.dao.mapper.UserCouponMapper;
import com.duobei.core.user.user.domain.criteria.UserCouponCriteria;
import com.duobei.core.user.user.domain.vo.UserCouponVo;
import com.duobei.core.user.user.service.UserCouponService;
import com.duobei.dic.ZD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    @Autowired
    private BorrowCashDao borrowCashDao;
    @Autowired
    private BorrowCashRepaymentDao repaymentDao;


    @Override
    public List<UserCouponVo> getByUserIdAndState(Long id, Integer state) {
        String orderName;
        if (state == ZD.couponState_new){
            orderName = "add_time";
        }else if(state == ZD.couponState_used){
            orderName = "used_time";
        }else if(state == ZD.couponState_expire){
            orderName = "end_time";
        }else if (state == ZD.couponState_frozen){
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
            Coupon coupon = couponMap.get(new Long(userCouponVo.getCouponId()));
            if (coupon != null){
                userCouponVo.setCouponName(coupon.getCouponName());
                userCouponVo.setAmount(new BigDecimal(coupon.getAmount()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP));
            }
        }
        return list;
    }

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<UserCouponVo> getPage(UserCouponCriteria criteria) {
        int total = userCouponDao.countByCriteria(criteria);
        List<UserCouponVo> list = null;
        if (total > 0) {
            list = userCouponDao.getPage(criteria);
        }
        //查询使用该优惠券的借款订单或还款订单
        List<HashMap<String,Object>> couponIds = new ArrayList<>();
        for(UserCouponVo vo : list){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("couponId",vo.getCouponId());
            map.put("userId",vo.getUserId());
            map.put("productId",vo.getProductId());
        }
        List<BorrowCash> borrowCashes = borrowCashDao.getByCouponIds(couponIds);
        if( borrowCashes.size() > BizConstant.INT_ZERO){
            for( UserCouponVo vo :list){
                loop1:for (BorrowCash cash :borrowCashes){
                    if(cash.getCouponId().equals(vo.getCouponId())){
                        vo.setBorrowNo(cash.getBorrowNo());
                        break loop1;
                    }
                }
            }
        }

        return new ListVo<UserCouponVo>(total, list);
    }
}
