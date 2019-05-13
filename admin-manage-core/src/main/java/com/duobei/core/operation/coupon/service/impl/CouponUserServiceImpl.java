package com.duobei.core.operation.coupon.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.coupon.dao.CouponDao;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.domain.CouponUser;
import com.duobei.core.transaction.borrow.dao.BorrowCashDao;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.repayment.dao.BorrowCashRepaymentDao;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.operation.coupon.dao.CouponUserDao;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.operation.coupon.dao.mapper.CouponUserMapper;
import com.duobei.core.user.user.domain.User;
import com.duobei.core.operation.coupon.domain.criteria.CouponUserCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponUserVo;
import com.duobei.core.operation.coupon.service.CouponUserService;
import com.duobei.dic.ZD;
import com.duobei.utils.DateUtil;
import com.pgy.data.handler.PgyDataHandler;
import com.pgy.data.handler.service.PgyDataHandlerService;
import com.pgy.data.handler.service.impl.PgyDataHandlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
@Service("couponUserService")
public class CouponUserServiceImpl implements CouponUserService {
    @Autowired
    private CouponUserDao couponUserDao;
    @Autowired
    private BorrowCashDao borrowCashDao;
    @Autowired
    private BorrowCashRepaymentDao repaymentDao;
    @Autowired
    private UserDao userDao;


    /**
     * 批量写入优惠券
     * @param temp
     */
    @Override
    public void batchSave(List<CouponUser> temp){
        couponUserDao.batchSave(temp);
    }

    @Override
    public List<CouponUserVo> getByUserIdAndState(Long id, Integer state) {
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
        List<CouponUserVo> list = couponUserDao.getByUserIdAndState(id,state,orderName);
        for (CouponUserVo vo : list){
            vo.setCouponTypeName(DictUtil.getDictLabel(ZD.couponType,vo.getCouponType()));
            vo.setAmount(vo.getAmount().divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP));

        }

        return list;
    }

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<CouponUserVo> getPage(CouponUserCriteria criteria) {
        //根据用户编号查询用户id
        if (!StringUtil.isBlank(criteria.getPhone())) {
            PgyDataHandlerService pgyDataHandlerService = new PgyDataHandlerServiceImpl();
            criteria.setPhone(pgyDataHandlerService.encrypt(criteria.getPhone()));
            User user = userDao.getByUserNameEncrypt(criteria.getPhone(),null);
            if (user != null) {
                criteria.setUserId(user.getId());
            }else{
                return new ListVo<CouponUserVo>(BizConstant.INT_ZERO, new ArrayList<>());
            }
        }
        //订单号查询条件
        if (!StringUtil.isBlank(criteria.getOrderNo())) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("orderNo", criteria.getOrderNo());
            map.put("userId", criteria.getUserId());
            map.put("productId", criteria.getProductId());
            //有借款订单查借款订单
            BorrowCash cash = borrowCashDao.getByBorrowNoMap(map);
            if (cash == null) {
                //没有去查还款订单
                BorrowCashRepayment repayment = repaymentDao.getByBorrowNoMap(map);
                if (repayment == null) {
                    return new ListVo<CouponUserVo>(BizConstant.INT_ZERO, new ArrayList<>());
                }else{
                    criteria.setCouponId(cash.getCouponId());
                }
            }else{
                criteria.setCouponId(cash.getCouponId());
            }
        }
        int total = couponUserDao.countByCriteria(criteria);
        List<CouponUserVo> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = couponUserDao.getPage(criteria);
            List<Integer> couponIds = new ArrayList<>();
            List<Long> userIds = new ArrayList<>();
            //优惠券使用状态
            for(CouponUserVo vo: list){
                if( !vo.getState().equals(ZD.couponState_used) ){
                    if( DateUtil.compare(new Date(),vo.getEndTime()) ){
                        vo.setState(ZD.couponState_expire);
                    }
                }
                userIds.add(vo.getUserId());
                couponIds.add(vo.getCouponId());
            }
            //用户编号设置
            List<User> userList = userDao.getByIds(criteria.getProductId(),userIds);
            for(CouponUserVo vo: list){
                for(User user : userList){
                    if(vo.getUserId() .equals(user.getId())){
                        vo.setUserNameEncrypt(PgyDataHandler.decrypt(user.getUserNameEncrypt()));
                        break;
                    }
                }
            }
            //返回订单编号
            //借款券
            List<HashMap<String,Object>> couponIds1 = new ArrayList<>();
            //还款券
            List<HashMap<String,Object>> couponIds2 = new ArrayList<>();
            for(CouponUserVo vo : list){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("userId",vo.getUserId());
                map.put("productId",vo.getProductId());
                map.put("couponId",vo.getCouponId());
                if( vo.getCouponType().equals(ZD.couponType_jkq)){
                    couponIds1.add(map);
                }else if(vo.getCouponType().equals(ZD.couponType_hkq)){
                    couponIds2.add(map);
                }
            }
            boolean flag = false;//表是订单号查询是否有结果
            if( couponIds1.size() > BizConstant.INT_ZERO){
                //查询使用该优惠券的借款订单
                List<BorrowCash> borrowCashes = borrowCashDao.getByCouponIds(couponIds1);
                if( borrowCashes.size() > BizConstant.INT_ZERO){
                    for( CouponUserVo vo :list){
                        for (BorrowCash cash :borrowCashes){
                            if( vo.getUsedTime()!=null && vo.getState().equals(ZD.couponState_used)){
                                //优惠券使用时间和订单提交时间在一分钟之内
                                if(vo.getCouponId() == cash.getCouponId().intValue() && DateUtil.minuteBetween(vo.getUsedTime(),cash.getAddTime())<BizConstant.INT_ONE){
                                    vo.setOrderNo(cash.getBorrowNo());
                                    vo.setBorrowId(cash.getId());
                                    if(!StringUtil.isBlank(criteria.getOrderNo()) && criteria.getOrderNo().equals(cash.getBorrowNo())){
                                        list = new ArrayList<>();
                                        list.add(vo);
                                        flag = true;
                                    }else{
                                        borrowCashes.remove(cash);
                                        break;
                                    }
                                }
                            }
                        }
                        if (flag) {
                            break;
                        }
                    }
                }
            }
            if( couponIds2.size() > BizConstant.INT_ONE && !flag){
                //查询使用该优惠券的还款订单
                List<BorrowCashRepayment> repayments = repaymentDao.getByCouponIds(couponIds2);
                if( repayments.size() > BizConstant.INT_ZERO){
                    for( CouponUserVo vo :list){
                        for (BorrowCashRepayment e :repayments){
                            if( vo.getUsedTime()!=null && vo.getState().equals(ZD.couponState_used)){
                                if(vo.getCouponId() == e.getCouponId().intValue() && DateUtil.minuteBetween(vo.getUsedTime(),e.getAddTime())<BizConstant.INT_ONE){
                                    vo.setOrderNo(e.getRepayNo());
                                    vo.setBorrowId(e.getBorrowCashId());
                                    if(!StringUtil.isBlank(criteria.getOrderNo()) && criteria.getOrderNo().equals(e.getRepayNo())){
                                        list = new ArrayList<>();
                                        list.add(vo);
                                        flag = true;
                                    }else{
                                        repayments.remove(e);
                                        break;
                                    }
                                }
                            }
                        }
                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }
        return new ListVo<CouponUserVo>(total, list);
    }
}
