package com.duobei.core.message.sms.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class SmsRecord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 应用
     */
    private String appKey;

    /**
     * 类别. 常规: NORMAL, 催收: COLLECTION, 营销: MARKETING
     */
    private String businessCode;

    /**
     * 短信编码
     */
    private String templetCode;

    /**
     * 发送手机号
     */
    private String sendMobile;

    /**
     * 短信内容
     */
    private String sendContent;

    /**
     * 状态. 发送状态：0未发送，1发送成功，2发送失败
     */
    private Boolean sendState;

    /**
     * 备注
     */
    private String remake;

    /**
     * 
     */
    private String createUser;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String updateUser;

    /**
     * 
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pub_sms_record
     *
     * @mbg.generated Tue May 14 16:32:46 CST 2019
     */
    private static final long serialVersionUID = 6607893819547465190L;

}