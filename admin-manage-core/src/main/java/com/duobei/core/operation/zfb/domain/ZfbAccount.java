package com.duobei.core.operation.zfb.domain;

import java.io.Serializable;
import java.util.Date;

public class ZfbAccount implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 账号名称
     */
    private String name;

    /**
     * 支付账号
     */
    private String account;

    /**
     * 是否启用【0：禁用，1：启用】
     */
    private Integer isEnable;

    /**
     * 二维码
     */
    private String qrcode;

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
     * This field corresponds to the database table yy_zfb_account
     *
     * @mbg.generated Fri Apr 12 13:45:29 CST 2019
     */
    private static final long serialVersionUID = 3189385480502094343L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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
     * This method corresponds to the database table yy_zfb_account
     *
     * @mbg.generated Fri Apr 12 13:45:29 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", name=").append(name);
        sb.append(", account=").append(account);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", qrcode=").append(qrcode);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}