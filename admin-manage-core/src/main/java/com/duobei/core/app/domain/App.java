package com.duobei.core.app.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Data
public class App implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 应用唯一key
     */
    private String appKey;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 应用类型：1主包，2马甲包
     */
    private Byte appType;

    /**
     * 状态：1：有效；0：无效
     */
    private Byte state;

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
     * 
     */
    private Integer isDelete;


}