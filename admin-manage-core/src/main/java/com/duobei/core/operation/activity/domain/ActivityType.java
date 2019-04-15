package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityType implements Serializable {
    /**
     * 活动类型id
     */
    private Integer atId;

    /**
     * 活动类型编码
     */
    private String atCode;

    /**
     * 活动类型名称
     */
    private String atName;

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