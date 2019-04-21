package com.duobei.core.user.user.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
@Data
public class UserAuthListVo {
    /**
     * 认证项
     */
    private String authName;

    /**
     * 认证状态
     */
    private String authState;

    /**
     * 认证完成事件
     */
    private Date authTime;

    /**
     * 操作类型 0:无 1:重置reset
     */
    private String operState;

    private String code;
}
