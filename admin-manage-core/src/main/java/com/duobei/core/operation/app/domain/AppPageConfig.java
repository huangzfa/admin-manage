package com.duobei.core.operation.app.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppPageConfig implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单唯一编码
     */
    private String menuCode;

    /**
     * 是否启用【0：禁用，1：启用】
     */
    private Boolean isEnable;

    /**
     * 菜单排序
     */
    private Integer menuSort;

    /**
     * 菜单类型【1：h5，2：原生】
     */
    private Byte menuType;

    /**
     * 菜单链接值：h5时为URL，原生时为页面code
     */
    private String menuVal;

    /**
     * 未选中图标url
     */
    private String iconUrl;

    /**
     * 选中图标url
     */
    private String selectIconUrl;

    /**
     * 是否是应用审核状态下使用【0：非审核使用，1：审核使用】
     */
    private Boolean isExamine;

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
    private Integer isDelete;

}