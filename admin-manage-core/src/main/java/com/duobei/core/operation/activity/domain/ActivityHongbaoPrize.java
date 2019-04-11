package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityHongbaoPrize implements Serializable {
    /**
     * 活动奖品关联id,主键
     */
    private Integer actPrizeId;

    /**
     * 活动id
     */
    private Integer actId;

    /**
     * 奖品id
     */
    private Long prizeId;

    /**
     * 对应天数：7，15，25
     */
    private Integer days;

    /**
     * 
     */
    private String createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDelete;


}