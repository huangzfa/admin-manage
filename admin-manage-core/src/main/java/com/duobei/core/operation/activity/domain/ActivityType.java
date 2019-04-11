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