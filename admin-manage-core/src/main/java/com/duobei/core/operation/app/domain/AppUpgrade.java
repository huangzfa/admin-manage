package com.duobei.core.operation.app.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Data
public class AppUpgrade implements Serializable {
    /**
     * 主键，自增id
     */
    private Integer id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 状态 【0：关闭状态， 1：开启状态】，字典
     */
    private Byte state;

    /**
     * 版本号
     */
    private Integer versionNumber;

    /**
     * 版本名，展示给客户看到的版本
     */
    private String versionName;

    /**
     * 升级范围 【0：针对所有版本 ，1：针对部分版本】，字典
     */
    private Byte upgradeRange;

    /**
     * upgrade_rang为针对部分版本升级时，需要升级的最小（含）版本
     */
    private Integer minVersionNumber;

    /**
     * upgrade_rang为针对部分版本升级时，需要升级的最大（含）版本
     */
    private Integer maxVersionNumber;

    /**
     * 是否强制升级 【0：否，1：是】
     */
    private Boolean isForce;

    /**
     * 是否静默升级 【0：否，1：是】
     */
    private Boolean isSilence;

    /**
     * apk包下载地址
     */
    private String apkUrl;

    /**
     * 下载包md5
     */
    private String apkMd5;

    /**
     * 限制应用类型【ios：苹果，android：安卓】，字典
     */
    private String appOsType;

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

    /**
     * 当前版本描述
     */
    private String versionRemark;


}