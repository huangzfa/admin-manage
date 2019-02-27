package com.duobei.core.operation.product.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Product implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 所属商户id
     */
    private Integer merchantId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品编码，初始化后不能修改
     */
    private String productCode;

    /**
     * 平台状态：1启用，0停用
     */
    private Byte state;

    /**
     * 产品描述
     */
    private String description;

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