package com.duobei.core.operation.product.domain;

import java.io.Serializable;
import java.util.Date;

public class Business implements Serializable {
    /**
     * 业务编码，主键
     */
    private String bizCode;

    /**
     * 业务名称
     */
    private String bizName;

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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_business
     *
     * @mbg.generated Tue Mar 05 15:17:26 CST 2019
     */
    private static final long serialVersionUID = 4904389585364153715L;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
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

    public Integer getModifyOperatorId() {
        return modifyOperatorId;
    }

    public void setModifyOperatorId(Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business
     *
     * @mbg.generated Tue Mar 05 15:17:26 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bizCode=").append(bizCode);
        sb.append(", bizName=").append(bizName);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append("]");
        return sb.toString();
    }
}