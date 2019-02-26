package com.duobei.core.product.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductAppChannel implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 所属应用id
     */
    private Integer appId;

    /**
     * 推广渠道id
     */
    private Integer channelId;

    /**
     * 渠道样式id
     */
    private Integer channelStyleId;

    /**
     * 是否启用【0：禁用，1：启用】
     */
    private Boolean isEnable;

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
     * This field corresponds to the database table yy_product_app_channel
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    private static final long serialVersionUID = 7910124035414259783L;

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

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getChannelStyleId() {
        return channelStyleId;
    }

    public void setChannelStyleId(Integer channelStyleId) {
        this.channelStyleId = channelStyleId;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
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
     * This method corresponds to the database table yy_product_app_channel
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", appId=").append(appId);
        sb.append(", channelId=").append(channelId);
        sb.append(", channelStyleId=").append(channelStyleId);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append("]");
        return sb.toString();
    }
}