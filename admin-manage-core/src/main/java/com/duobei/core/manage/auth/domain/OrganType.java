package com.duobei.core.manage.auth.domain;

import java.io.Serializable;
import java.util.Date;

public class OrganType implements Serializable {
    /**
     * 
     */
    private Integer organTypeId;

    /**
     * 
     */
    private String organTypeCode;

    /**
     * 
     */
    private String organTypeName;

    /**
     * 
     */
    private Boolean isSystem;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table aa_organ_type
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    private static final long serialVersionUID = -7807266953080327450L;

    public Integer getOrganTypeId() {
        return organTypeId;
    }

    public void setOrganTypeId(Integer organTypeId) {
        this.organTypeId = organTypeId;
    }

    public String getOrganTypeCode() {
        return organTypeCode;
    }

    public void setOrganTypeCode(String organTypeCode) {
        this.organTypeCode = organTypeCode;
    }

    public String getOrganTypeName() {
        return organTypeName;
    }

    public void setOrganTypeName(String organTypeName) {
        this.organTypeName = organTypeName;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
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

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_type
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organTypeId=").append(organTypeId);
        sb.append(", organTypeCode=").append(organTypeCode);
        sb.append(", organTypeName=").append(organTypeName);
        sb.append(", isSystem=").append(isSystem);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append("]");
        return sb.toString();
    }
}