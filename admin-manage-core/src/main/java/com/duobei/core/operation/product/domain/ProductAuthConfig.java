package com.duobei.core.operation.product.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductAuthConfig implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 业务编码
     */
    private String bizCode;

    /**
     * 认证项id
     */
    private Integer authId;

    /**
     * 是否启用【0：禁用，1：启用】
     */
    private Boolean isEnable;

    /**
     * 是否必填【0：不必填，1：必填】
     */
    private Boolean isRequired;

    /**
     * 认证顺序：升序
     */
    private Integer authSort;

    /**
     * 认证组，同组才能设置几选几
     */
    private String authGroup;

    /**
     * 选择类型：3s1：三选一，空不限制
     */
    private String selectType;

    /**
     * 应用于应用系统类型【ios：苹果，android：安卓】，字典，空为应用于所有
     */
    private String appOsType;

    /**
     * 应用于苹果版本：多个以,分隔，空不限制
     */
    private String iosVersion;

    /**
     * 应用于安卓版本：多个以,分隔，空不限制
     */
    private String androidVersion;

    /**
     * 有效期单位【1：天，2：小时】，字典
     */
    private String validUnit;

    /**
     * 有效期值，小于等于0表示不限制
     */
    private Integer validVal;

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

}