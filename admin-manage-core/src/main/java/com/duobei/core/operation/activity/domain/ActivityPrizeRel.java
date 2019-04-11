package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityPrizeRel implements Serializable {
    /**
     * 主键
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
     * 获奖概率
     */
    private Double chance;

    /**
     * 是否有效1无效0有效
     */
    private Integer isDelete;

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

}