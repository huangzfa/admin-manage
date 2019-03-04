package com.duobei.core.operation.banner.domain;

import java.io.Serializable;
import java.util.Date;

public class Banner implements Serializable {
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
     * 图片URL
     */
    private String imgUrl;

    /**
     * 标题
     */
    private String bannerTitle;

    /**
     * 类型【】，字典
     */
    private String bannerType;

    /**
     * 链接类型【0：无跳转，1：URL】，字典
     */
    private String redirectType;

    /**
     * 跳转链接URL
     */
    private String redirectUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序，越小越靠前
     */
    private Integer sort;

    /**
     * 是否需要登录【0：不需要，1：需要】
     */
    private Integer isNeedLogin;

    /**
     * 是否启用【0：禁用，1：启用】
     */
    private Integer isEnable;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 添加人id
     */
    private Integer addOperatorId;

    /**
     * 修改人id
     */
    private Integer modifyOperatorId;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_banner
     *
     * @mbg.generated Sat Mar 02 18:00:45 CST 2019
     */
    private static final long serialVersionUID = -41369275339721782L;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(String redirectType) {
        this.redirectType = redirectType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsNeedLogin() {
        return isNeedLogin;
    }

    public void setIsNeedLogin(Integer isNeedLogin) {
        this.isNeedLogin = isNeedLogin;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_banner
     *
     * @mbg.generated Sat Mar 02 18:00:45 CST 2019
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
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", bannerTitle=").append(bannerTitle);
        sb.append(", bannerType=").append(bannerType);
        sb.append(", redirectType=").append(redirectType);
        sb.append(", redirectUrl=").append(redirectUrl);
        sb.append(", remark=").append(remark);
        sb.append(", sort=").append(sort);
        sb.append(", isNeedLogin=").append(isNeedLogin);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}