package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityExchangePrize implements Serializable {
    /**
     * 
     */
    private Integer actPrizeId;

    /**
     * 活动id
     */
    private Integer actId;

    /**
     * 奖品id
     */
    private Integer prizeId;

    /**
     * 兑换数量
     */
    private Integer exchangeNum;

    /**
     * 
     */
    private Integer isDelete;

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