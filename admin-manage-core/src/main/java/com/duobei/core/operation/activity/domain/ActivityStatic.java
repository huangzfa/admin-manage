package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityStatic implements Serializable {
    /**
     * square 方角 fillet圆角
     */
    private Integer actId;

    /**
     * 按钮类型
     */
    private String btnType;

    /**
     * 按钮文案
     */
    private String btnText;

    /**
     * 按钮颜色
     */
    private String btnColour;

    /**
     * 按钮背景图
     */
    private String btnImg;

    /**
     * 跳转类型 app ,other
     */
    private String jumpType;

    /**
     * 跳转链接
     */
    private String jumpLink;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 添加人id
     */
    private Integer addOperatorId;

    /**
     * 修改人id
     */
    private Integer modifyOperatorId;

    /**
     * 活动规则是否显示0不显示
     */
    private Byte ruleEnable;


}