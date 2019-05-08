package com.duobei.core.operation.coupon.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Coupon implements Serializable {
    /**
     * 主键，自增id
     */
    private Long id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 劵名称
     */
    private String couponName;

    /**
     * 优惠券类型，字典
     */
    private String couponType;

    /**
     * 券金额
     */
    private Long amount;

    /**
     * 限制使用金额，0表示不限制，如借款满1000可用
     */
    private Long limitAmount;

    /**
     * 使用说明，给用户看
     */
    private String useExplain;

    /**
     * 优惠券发放总数 -1不限制
     */
    private Integer quota;

    /**
     * 已经发放数量
     */
    private Integer quotaSend;

    /**
     * 每个人限制领取张数，-1不限制
     */
    private Integer personLimitCount;

    /**
     * 有效期类型【0：固定天数，1：固定时间范围】，字典
     */
    private Integer expiryType;

    /**
     * 有效期天数，有效期为0时使用
     */
    private Integer validDays;

    /**
     * 有效期开始时间，有效期为1时使用
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtStart;

    /**
     * 有效期结束时间，有效期为1时使用
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtEnd;

    /**
     * 领取类型【0：发放后即可，1：限时】，字典
     */
    private Integer receiveType;

    /**
     * 领取开始时间
     */
    private Date receiveStart;

    /**
     * 领取结束时间
     */
    private Date receiveEnd;

    /**
     * 备注，用于运营看
     */
    private String remark;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 
     */
    private Integer addOperatorId;

    /**
     * 
     */
    private Integer modifyOperatorId;

    /**
     * 
     */
    private Integer isDelete;

}