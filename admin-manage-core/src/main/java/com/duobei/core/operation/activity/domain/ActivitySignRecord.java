package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivitySignRecord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 活动id
     */
    private Integer actId;

    /**
     * 活动类型编码
     */
    private String atCode;

    /**
     * 
     */
    private Long userId;

    /**
     * 累计次数
     */
    private Integer total;

    /**
     * 年月日，如：20180731
     */
    private Integer gmtYmd;

    /**
     * 
     */
    private Date createTime;


}