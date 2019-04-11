package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityTakeRecord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 活动id
     */
    private Integer actId;

    /**
     * 奖品活动关联表id
     */
    private Integer actPrizeId;

    /**
     * 其他红包奖品关联表id
     */
    private Integer hongbaoPrizeId;

    /**
     * 活动类型编码
     */
    private String atCode;

    /**
     * 奖品id，如果用户没有中奖，则为-1
     */
    private Integer prizeId;

    /**
     * 
     */
    private String prizeName;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private String phone;

    /**
     * 使用之前次数
     */
    private Integer beforeUsedTimes;

    /**
     * 使用之后次数
     */
    private Integer afterUsedTimes;

    /**
     * 
     */
    private Integer isDelete;

    /**
     * 年月日，如：20180731
     */
    private Integer gmtYmd;

    /**
     * 
     */
    private Date createTime;

    /**
     * 借款券jqk ,还款券hkq
     */
    private String prizeType;

}