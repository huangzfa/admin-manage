package com.duobei.core.operation.coupon.service.impl;

import com.duobei.common.util.BeanUtil;
import com.duobei.core.operation.coupon.domain.CouponUser;
import com.duobei.core.operation.coupon.service.CouponUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

/**优惠券发送线程类
 * Created by huangzhognfa on 2018/12/5.
 */
public class SendCouponThread implements Callable<String> {
    private final static Logger log = LoggerFactory.getLogger(SendCouponThread.class);
    private List<CouponUser> temp;

    @Override
    public String call() throws Exception {
        CouponUserService couponUserService = (CouponUserService) BeanUtil.getBean("couponUserService");
        try {
            couponUserService.batchSave(temp);
            return null;
        }catch (Exception e){
            log.warn("SendCouponThread优惠券发送失败",e);
           return "优惠券发送失败";
        }
    }

    public List<CouponUser> getTemp() {
        return temp;
    }

    public void setTemp(List<CouponUser> temp) {
        this.temp = temp;
    }
}
