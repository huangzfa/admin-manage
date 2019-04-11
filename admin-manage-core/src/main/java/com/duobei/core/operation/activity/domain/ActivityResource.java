package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityResource implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源地址
     */
    private String value;

    /**
     * 环境类型 preview 预发环境 test 测试环境 online线上环境
     */
    private String envirType;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String createBy;

    /**
     * 
     */
    private Integer isDelete;

}