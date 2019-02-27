package com.duobei.core.operation.app.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppExamine implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 审核应用类型【ios：苹果，android：安卓】，字典，空为不限制
     */
    private String appOsType;

    /**
     * 渠道id
     */
    private Integer channelId;

    /**
     * 版本号
     */
    private Integer versionNumber;

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
     * 删除标志，0有效，其他值表示已删除
     */
    private Long isDelete;

}