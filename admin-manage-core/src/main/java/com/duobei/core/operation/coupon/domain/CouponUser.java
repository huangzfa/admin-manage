package com.duobei.core.operation.coupon.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CouponUser implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 优惠券id
     */
    private Integer couponId;

    /**
     * 优惠券类型【jk：借款劵，hk：还款劵】，字典
     */
    private String couponType;

    /**
     * 劵状态 【 0：未使用，1：已使用，2：冻结，3：过期 】，字典
     */
    private Integer state;

    /**
     * 是否有期限【1：有，0：无】
     */
    private Integer timeFlag;

    /**
     * 有效期开始时间
     */
    private Date startTime;

    /**
     * 有效期结束时间
     */
    private Date endTime;

    /**
     * 劵使用时间
     */
    private Date usedTime;

    /**
     * 获取来源【1：注册，2：抽奖】，字典
     */
    private String fromType;

    /**
     * 来源关联：如果是系统的就sys，如果是邀请就是邀请人的ID
     */
    private String fromRef;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 删除标志，0有效，其他值表示已删除
     */
    private Long isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pgy_user_coupon
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    private static final long serialVersionUID = -8301452035430396196L;

}