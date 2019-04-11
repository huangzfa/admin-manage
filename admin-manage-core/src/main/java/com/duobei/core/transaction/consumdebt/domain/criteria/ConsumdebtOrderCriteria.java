package com.duobei.core.transaction.consumdebt.domain.criteria;

import com.duobei.common.util.Pagination;

import java.util.Date;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/8
 */
public class ConsumdebtOrderCriteria extends Pagination {

    /**
     * 借贷商品订单号
     */
    private String orderNo;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 快递单号
     */
    private String logisticsNo;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态
     */
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
