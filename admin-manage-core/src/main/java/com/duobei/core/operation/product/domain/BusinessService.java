package com.duobei.core.operation.product.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BusinessService implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务编码
     */
    private String bizCode;

    /**
     * 服务编码
     */
    private String serviceCode;

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