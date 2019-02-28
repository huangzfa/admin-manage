package com.duobei.core.manage.auth.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {
    /**
     * 
     */
    private Integer roleId;

    /**
     * 
     */
    private String roleName;

    /**
     * 
     */
    private String roleState;

    /**
     * 
     */
    private String remark;

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
    private String addOperatorName;

    /**
     * 
     */
    private Integer modifyOperatorId;

    /**
     * 
     */
    private String modifyOperatorName;


}