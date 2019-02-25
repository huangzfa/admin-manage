package com.duobei.core.message.domain;

import java.io.Serializable;
import java.util.Date;

public class JpushChannel implements Serializable {
    /**
     * 极光渠道ID
     */
    private Integer id;

    /**
     * 通道编码
     */
    private String jpushChannelCode;

    /**
     * 通道名称
     */
    private String jpushChannelName;

    /**
     * 平台
     */
    private String platform;

    /**
     * 极光appkey
     */
    private String appkey;

    /**
     * 极光secret
     */
    private String secret;

    /**
     * 1:启用 2：禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
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

    /**
     * 判断是否可用 0 可用 其他不可用
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pub_jpush_channel
     *
     * @mbg.generated Thu Dec 27 10:57:50 CST 2018
     */
    private static final long serialVersionUID = 8227066912152612245L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJpushChannelCode() {
        return jpushChannelCode;
    }

    public void setJpushChannelCode(String jpushChannelCode) {
        this.jpushChannelCode = jpushChannelCode;
    }

    public String getJpushChannelName() {
        return jpushChannelName;
    }

    public void setJpushChannelName(String jpushChannelName) {
        this.jpushChannelName = jpushChannelName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getAddOperatorId() {
        return addOperatorId;
    }

    public void setAddOperatorId(Integer addOperatorId) {
        this.addOperatorId = addOperatorId;
    }

    public String getAddOperatorName() {
        return addOperatorName;
    }

    public void setAddOperatorName(String addOperatorName) {
        this.addOperatorName = addOperatorName;
    }

    public Integer getModifyOperatorId() {
        return modifyOperatorId;
    }

    public void setModifyOperatorId(Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }

    public String getModifyOperatorName() {
        return modifyOperatorName;
    }

    public void setModifyOperatorName(String modifyOperatorName) {
        this.modifyOperatorName = modifyOperatorName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_jpush_channel
     *
     * @mbg.generated Thu Dec 27 10:57:50 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", jpushChannelCode=").append(jpushChannelCode);
        sb.append(", jpushChannelName=").append(jpushChannelName);
        sb.append(", platform=").append(platform);
        sb.append(", appkey=").append(appkey);
        sb.append(", secret=").append(secret);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", addOperatorName=").append(addOperatorName);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append(", modifyOperatorName=").append(modifyOperatorName);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}