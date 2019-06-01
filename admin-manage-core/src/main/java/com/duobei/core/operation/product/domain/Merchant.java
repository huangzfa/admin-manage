package com.duobei.core.operation.product.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Merchant implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商户唯一编号，从10000000
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 状态：1：有效；0：无效
     */
    private Integer state;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    private static final long serialVersionUID = 158904488572186951L;


}