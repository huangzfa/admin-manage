package com.duobei.core.operation.activity.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityPrize implements Serializable {
    /**
     * 奖品id
     */
    private Integer prizeId;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品类型，jkq借款券，hkq还款券，zyjp自有奖品等
     */
    private String prizeType;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 奖品金额
     */
    private BigDecimal money;

    /**
     * 奖品图片url
     */
    private String imgUrl;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 是否跳转
     */
    private Integer isJump;

    /**
     * 
     */
    private Integer isDelete;

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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    private static final long serialVersionUID = 1284375237462497766L;

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getIsJump() {
        return isJump;
    }

    public void setIsJump(Integer isJump) {
        this.isJump = isJump;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prizeId=").append(prizeId);
        sb.append(", prizeName=").append(prizeName);
        sb.append(", prizeType=").append(prizeType);
        sb.append(", couponId=").append(couponId);
        sb.append(", money=").append(money);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", link=").append(link);
        sb.append(", isJump=").append(isJump);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append("]");
        return sb.toString();
    }
}