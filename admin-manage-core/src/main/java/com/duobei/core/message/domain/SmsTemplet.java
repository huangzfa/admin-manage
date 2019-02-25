package com.duobei.core.message.domain;

import java.io.Serializable;
import java.util.Date;

public class SmsTemplet implements Serializable {
    /**
     * 短信模板ID
     */
    private Integer id;

    /**
     * 短信类型编码
     */
    private String smsUserfulCode;

    /**
     * 模板类型编码
     */
    private String templetCode;

    /**
     * 模板内容
     */
    private String templetContent;

    /**
     * 替换参数，多个用逗号分隔
     */
    private String templetParams;

    /**
     * 所属系统
     */
    private String platform;

    /**
     * 1:启用 2：禁用
     */
    private Integer status;

    /**
     * 模板用途说明
     */
    private String desc;

    /**
     * 每天限制发送短信次数 0：不限制
     */
    private Integer limitTime;

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
     * 
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pub_sms_templet
     *
     * @mbg.generated Mon Jan 07 11:50:29 CST 2019
     */
    private static final long serialVersionUID = -4346545770181875338L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmsUserfulCode() {
        return smsUserfulCode;
    }

    public void setSmsUserfulCode(String smsUserfulCode) {
        this.smsUserfulCode = smsUserfulCode;
    }

    public String getTempletCode() {
        return templetCode;
    }

    public void setTempletCode(String templetCode) {
        this.templetCode = templetCode;
    }

    public String getTempletContent() {
        return templetContent;
    }

    public void setTempletContent(String templetContent) {
        this.templetContent = templetContent;
    }

    public String getTempletParams() {
        return templetParams;
    }

    public void setTempletParams(String templetParams) {
        this.templetParams = templetParams;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
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
     * This method corresponds to the database table pub_sms_templet
     *
     * @mbg.generated Mon Jan 07 11:50:29 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", smsUserfulCode=").append(smsUserfulCode);
        sb.append(", templetCode=").append(templetCode);
        sb.append(", templetContent=").append(templetContent);
        sb.append(", templetParams=").append(templetParams);
        sb.append(", platform=").append(platform);
        sb.append(", status=").append(status);
        sb.append(", desc=").append(desc);
        sb.append(", limitTime=").append(limitTime);
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