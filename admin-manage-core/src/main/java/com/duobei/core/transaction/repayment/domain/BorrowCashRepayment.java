package com.duobei.core.transaction.repayment.domain;

import java.io.Serializable;
import java.util.Date;

public class BorrowCashRepayment implements Serializable {
    /**
     * 主键，自增id
     */
    private Long id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 借钱id
     */
    private Long borrowCashId;

    /**
     * 还款编号
     */
    private String repayNo;

    /**
     * 还款类型【0：主动还款，1：线下还款，2：自动扣款】
     */
    private Integer repayType;

    /**
     * 还款状态【0：新建状态，1：还款成功，2：处理中，-1：还款失败】
     */
    private Integer repayState;

    /**
     * 还款金额，【实际支付金额+优惠券金额】
     */
    private Long repayAmount;

    /**
     * 实际支付金额，分
     */
    private Long repayActualAmount;

    /**
     * 本次所还逾期费，分
     */
    private Long repayOverdueAmount;

    /**
     * 本次所还利息，分
     */
    private Long repayRateAmount;

    /**
     * 还款时_借款的当前待还款金额
     */
    private Long unpaidAmount;

    /**
     * 所用账户余额，分
     */
    private Long rebateAmount;

    /**
     * 请求资金代扣订单号【业务生成】
     */
    private String upsOrderNo;

    /**
     * 资金三方代扣订单号【资金系统回传】
     */
    private String upsThirdOrderNo;

    /**
     * 请求扣款时间
     */
    private Date gmtUpsReq;

    /**
     * 完成扣款时间
     */
    private Date gmtUpsFinish;

    /**
     * 到账账号类型：【1：银行卡，2：支付宝】
     */
    private Integer accountType;

    /**
     * 到账账号名（银行卡名称、支付宝名称）
     */
    private String accountName;

    /**
     * 到账账号（银行卡号、支付宝账号）脱敏
     */
    private String accountNo;

    /**
     * 到账账号密文无解，用于匹配
     */
    private String accountNoMd5;

    /**
     * 使用劵id，0表示未使用劵
     */
    private Long couponId;

    /**
     * 使用劵金额
     */
    private Long couponAmount;

    /**
     * 还款失败原因
     */
    private String failReason;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 数据版本，乐观锁
     */
    private Integer dataVersion;

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
    private Long isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tt_borrow_cash_repayment
     *
     * @mbg.generated Wed Mar 13 18:56:14 CST 2019
     */
    private static final long serialVersionUID = -8393755230983958121L;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBorrowCashId() {
        return borrowCashId;
    }

    public void setBorrowCashId(Long borrowCashId) {
        this.borrowCashId = borrowCashId;
    }

    public String getRepayNo() {
        return repayNo;
    }

    public void setRepayNo(String repayNo) {
        this.repayNo = repayNo;
    }

    public Integer getRepayType() {
        return repayType;
    }

    public void setRepayType(Integer repayType) {
        this.repayType = repayType;
    }

    public Integer getRepayState() {
        return repayState;
    }

    public void setRepayState(Integer repayState) {
        this.repayState = repayState;
    }

    public Long getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Long repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Long getRepayActualAmount() {
        return repayActualAmount;
    }

    public void setRepayActualAmount(Long repayActualAmount) {
        this.repayActualAmount = repayActualAmount;
    }

    public Long getRepayOverdueAmount() {
        return repayOverdueAmount;
    }

    public void setRepayOverdueAmount(Long repayOverdueAmount) {
        this.repayOverdueAmount = repayOverdueAmount;
    }

    public Long getRepayRateAmount() {
        return repayRateAmount;
    }

    public void setRepayRateAmount(Long repayRateAmount) {
        this.repayRateAmount = repayRateAmount;
    }

    public Long getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(Long unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public Long getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(Long rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getUpsOrderNo() {
        return upsOrderNo;
    }

    public void setUpsOrderNo(String upsOrderNo) {
        this.upsOrderNo = upsOrderNo;
    }

    public String getUpsThirdOrderNo() {
        return upsThirdOrderNo;
    }

    public void setUpsThirdOrderNo(String upsThirdOrderNo) {
        this.upsThirdOrderNo = upsThirdOrderNo;
    }

    public Date getGmtUpsReq() {
        return gmtUpsReq;
    }

    public void setGmtUpsReq(Date gmtUpsReq) {
        this.gmtUpsReq = gmtUpsReq;
    }

    public Date getGmtUpsFinish() {
        return gmtUpsFinish;
    }

    public void setGmtUpsFinish(Date gmtUpsFinish) {
        this.gmtUpsFinish = gmtUpsFinish;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNoMd5() {
        return accountNoMd5;
    }

    public void setAccountNoMd5(String accountNoMd5) {
        this.accountNoMd5 = accountNoMd5;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
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

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_repayment
     *
     * @mbg.generated Wed Mar 13 18:56:14 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", userId=").append(userId);
        sb.append(", borrowCashId=").append(borrowCashId);
        sb.append(", repayNo=").append(repayNo);
        sb.append(", repayType=").append(repayType);
        sb.append(", repayState=").append(repayState);
        sb.append(", repayAmount=").append(repayAmount);
        sb.append(", repayActualAmount=").append(repayActualAmount);
        sb.append(", repayOverdueAmount=").append(repayOverdueAmount);
        sb.append(", repayRateAmount=").append(repayRateAmount);
        sb.append(", unpaidAmount=").append(unpaidAmount);
        sb.append(", rebateAmount=").append(rebateAmount);
        sb.append(", upsOrderNo=").append(upsOrderNo);
        sb.append(", upsThirdOrderNo=").append(upsThirdOrderNo);
        sb.append(", gmtUpsReq=").append(gmtUpsReq);
        sb.append(", gmtUpsFinish=").append(gmtUpsFinish);
        sb.append(", accountType=").append(accountType);
        sb.append(", accountName=").append(accountName);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", accountNoMd5=").append(accountNoMd5);
        sb.append(", couponId=").append(couponId);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", failReason=").append(failReason);
        sb.append(", remark=").append(remark);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}