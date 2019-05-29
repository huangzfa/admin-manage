package com.duobei.core.operation.activity.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ActivityPrize implements Serializable {
    /**
     * 奖品id
     */
    private Integer prizeId;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品类型，jkq借款券，hkq还款券，zyjp自有奖品等
     */
    private String prizeType;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 奖品金额
     */
    private Long money;

    /**
     * 奖品图片url
     */
    private String imgUrl;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 是否跳转
     */
    private Integer isJump;

    /**
     * 
     */
    private Integer isDelete;


    private Integer state;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 添加人id
     */
    private Integer addOperatorId;

    /**
     * 修改人id
     */
    private Integer modifyOperatorId;


}