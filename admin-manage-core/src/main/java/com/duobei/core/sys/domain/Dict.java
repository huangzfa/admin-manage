package com.duobei.core.sys.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dict implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 上级
     */
    private Integer pid;

    /**
     * 
     */
    private String pVal;

    /**
     * 
     */
    private String dicCode;

    /**
     * 
     */
    private String dicVar;

    /**
     * 
     */
    private String dicVal;

    /**
     * 是否有子节点
     */
    private Boolean hasChild;

    /**
     * 
     */
    private String dataType;

    /**
     * 
     */
    private Integer dicSort;

    /**
     * 
     */
    private String des;


}