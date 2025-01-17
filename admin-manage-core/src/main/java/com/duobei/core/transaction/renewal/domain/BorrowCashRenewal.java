package com.duobei.core.transaction.renewal.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowCashRenewal implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 借款ID
     */
    private Long borrowCashId;

    /**
     * 续借编号
     */
    private String renewalNo;

    /**
     * 状态【0：新建状态，1：续期成功，2：处理中 ，-1：续期失败】
     */
    private Integer state;

    /**
     * 续借本金，分，500本金还200，这里为300
     */
    private Long renewalAmount;

    /**
     * 本次续期所还本金，分，500本金还200，这里为200
     */
    private Long capitalAmount;

    /**
     * 实付金额，分，本次续期所还本金+本期续借手续费206
     */
    private Long actualAmount;

    /**
     * 续期天数【7】
     */
    private Integer renewalDay;

    /**
     * 本期续借手续费，分，比如6
     */
    private Long renewalPoundage;

    /**
     * 续借手续费率，取借钱手续费日率
     */
    private BigDecimal poundageRate;

    /**
     * 续期所还逾期费，【实付金额-本次续期所还本金-本期续借手续费】
     */
    private Long renewalOverdueAmount;

    /**
     * 续借时，借款原预计还款时间
     */
    private Date gmtPlanRepayment;

    /**
     * 续期时_借款的央行利息
     */
    private Long bankRateAmount;

    /**
     * 续期时_借款的当前逾期费，根据逾期费上限而定
     */
    private Long overdueAmount;

    /**
     * 续期时_借款的当前实际逾期费
     */
    private Long realOverdueAmount;

    /**
     * 续期时_借款的当前未还款金额
     */
    private Long unpaidAmount;

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
     * 所用账户余额，分
     */
    private Long rebateAmount;

    /**
     * 续期失败原因，资金系统回传
     */
    private String failReason;

    /**
     * 备注
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
     * This field corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    private static final long serialVersionUID = 1816024399270334397L;

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

    public String getRenewalNo() {
        return renewalNo;
    }

    public void setRenewalNo(String renewalNo) {
        this.renewalNo = renewalNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getRenewalAmount() {
        return renewalAmount;
    }

    public void setRenewalAmount(Long renewalAmount) {
        this.renewalAmount = renewalAmount;
    }

    public Long getCapitalAmount() {
        return capitalAmount;
    }

    public void setCapitalAmount(Long capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public Long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getRenewalDay() {
        return renewalDay;
    }

    public void setRenewalDay(Integer renewalDay) {
        this.renewalDay = renewalDay;
    }

    public Long getRenewalPoundage() {
        return renewalPoundage;
    }

    public void setRenewalPoundage(Long renewalPoundage) {
        this.renewalPoundage = renewalPoundage;
    }

    public BigDecimal getPoundageRate() {
        return poundageRate;
    }

    public void setPoundageRate(BigDecimal poundageRate) {
        this.poundageRate = poundageRate;
    }

    public Long getRenewalOverdueAmount() {
        return renewalOverdueAmount;
    }

    public void setRenewalOverdueAmount(Long renewalOverdueAmount) {
        this.renewalOverdueAmount = renewalOverdueAmount;
    }

    public Date getGmtPlanRepayment() {
        return gmtPlanRepayment;
    }

    public void setGmtPlanRepayment(Date gmtPlanRepayment) {
        this.gmtPlanRepayment = gmtPlanRepayment;
    }

    public Long getBankRateAmount() {
        return bankRateAmount;
    }

    public void setBankRateAmount(Long bankRateAmount) {
        this.bankRateAmount = bankRateAmount;
    }

    public Long getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(Long overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public Long getRealOverdueAmount() {
        return realOverdueAmount;
    }

    public void setRealOverdueAmount(Long realOverdueAmount) {
        this.realOverdueAmount = realOverdueAmount;
    }

    public Long getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(Long unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
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

    public Long getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(Long rebateAmount) {
        this.rebateAmount = rebateAmount;
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
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
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
        sb.append(", renewalNo=").append(renewalNo);
        sb.append(", state=").append(state);
        sb.append(", renewalAmount=").append(renewalAmount);
        sb.append(", capitalAmount=").append(capitalAmount);
        sb.append(", actualAmount=").append(actualAmount);
        sb.append(", renewalDay=").append(renewalDay);
        sb.append(", renewalPoundage=").append(renewalPoundage);
        sb.append(", poundageRate=").append(poundageRate);
        sb.append(", renewalOverdueAmount=").append(renewalOverdueAmount);
        sb.append(", gmtPlanRepayment=").append(gmtPlanRepayment);
        sb.append(", bankRateAmount=").append(bankRateAmount);
        sb.append(", overdueAmount=").append(overdueAmount);
        sb.append(", realOverdueAmount=").append(realOverdueAmount);
        sb.append(", unpaidAmount=").append(unpaidAmount);
        sb.append(", upsOrderNo=").append(upsOrderNo);
        sb.append(", upsThirdOrderNo=").append(upsThirdOrderNo);
        sb.append(", gmtUpsReq=").append(gmtUpsReq);
        sb.append(", gmtUpsFinish=").append(gmtUpsFinish);
        sb.append(", accountType=").append(accountType);
        sb.append(", accountName=").append(accountName);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", accountNoMd5=").append(accountNoMd5);
        sb.append(", rebateAmount=").append(rebateAmount);
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