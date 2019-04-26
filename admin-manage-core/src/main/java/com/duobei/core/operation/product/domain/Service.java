package com.duobei.core.operation.product.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Service implements Serializable {
    /**
     * 服务编码，主键
     */
    private String serviceCode;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 商户信息同步接口URL，空表示不同步
     */
    private String merchantDatasynUrl;

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