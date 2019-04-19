package com.duobei.core.operation.coupon.domain;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable {
    /**
     * 主键，自增id
     */
    private Long id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 劵名称
     */
    private String couponName;

    /**
     * 优惠券类型，字典
     */
    private String couponType;

    /**
     * 券金额
     */
    private Long amount;

    /**
     * 限制使用金额，0表示不限制，如借款满1000可用
     */
    private Long limitAmount;

    /**
     * 使用说明，给用户看
     */
    private String useExplain;

    /**
     * 优惠券发放总数 -1不限制
     */
    private Integer quota;

    /**
     * 已经发放数量
     */
    private Integer quotaSend;

    /**
     * 每个人限制领取张数，-1不限制
     */
    private Integer personLimitCount;

    /**
     * 有效期类型【0：固定天数，1：固定时间范围】，字典
     */
    private Integer expiryType;

    /**
     * 有效期天数，有效期为0时使用
     */
    private Integer validDays;

    /**
     * 有效期开始时间，有效期为1时使用
     */
    private Date gmtStart;

    /**
     * 有效期结束时间，有效期为1时使用
     */
    private Date gmtEnd;

    /**
     * 领取类型【0：发放后即可，1：限时】，字典
     */
    private Integer receiveType;

    /**
     * 领取开始时间
     */
    private Date receiveStart;

    /**
     * 领取结束时间
     */
    private Date receiveEnd;

    /**
     * 备注，用于运营看
     */
    private String remark;

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
     * 
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_coupon
     *
     * @mbg.generated Wed Apr 17 10:08:27 CST 2019
     */
    private static final long serialVersionUID = 4159452108894564978L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Long limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getUseExplain() {
        return useExplain;
    }

    public void setUseExplain(String useExplain) {
        this.useExplain = useExplain;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getQuotaSend() {
        return quotaSend;
    }

    public void setQuotaSend(Integer quotaSend) {
        this.quotaSend = quotaSend;
    }

    public Integer getPersonLimitCount() {
        return personLimitCount;
    }

    public void setPersonLimitCount(Integer personLimitCount) {
        this.personLimitCount = personLimitCount;
    }

    public Integer getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(Integer expiryType) {
        this.expiryType = expiryType;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Date getReceiveStart() {
        return receiveStart;
    }

    public void setReceiveStart(Date receiveStart) {
        this.receiveStart = receiveStart;
    }

    public Date getReceiveEnd() {
        return receiveEnd;
    }

    public void setReceiveEnd(Date receiveEnd) {
        this.receiveEnd = receiveEnd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon
     *
     * @mbg.generated Wed Apr 17 10:08:27 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", couponName=").append(couponName);
        sb.append(", couponType=").append(couponType);
        sb.append(", amount=").append(amount);
        sb.append(", limitAmount=").append(limitAmount);
        sb.append(", useExplain=").append(useExplain);
        sb.append(", quota=").append(quota);
        sb.append(", quotaSend=").append(quotaSend);
        sb.append(", personLimitCount=").append(personLimitCount);
        sb.append(", expiryType=").append(expiryType);
        sb.append(", validDays=").append(validDays);
        sb.append(", gmtStart=").append(gmtStart);
        sb.append(", gmtEnd=").append(gmtEnd);
        sb.append(", receiveType=").append(receiveType);
        sb.append(", receiveStart=").append(receiveStart);
        sb.append(", receiveEnd=").append(receiveEnd);
        sb.append(", remark=").append(remark);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}