package com.duobei.core.operation.push.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class PushFailUser implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 推送配置id
     */
    private Integer pushId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 
     */
    private Integer productId;

    /**
     * 
     */
    private Integer appId;

    /**
     * 失败原因
     */
    private String reason;

    /**
     * 
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    private static final long serialVersionUID = -7934284317486443197L;


}