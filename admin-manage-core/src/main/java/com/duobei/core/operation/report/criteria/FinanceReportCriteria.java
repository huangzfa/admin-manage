package com.duobei.core.operation.report.criteria;

import com.duobei.common.util.Pagination;
import com.duobei.core.operation.product.domain.Product;

import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/1
 */
public class FinanceReportCriteria extends Pagination {

    private  Integer productId;

    private Integer state;

    private Integer reportType;

    private Date startTime;

    private Date endTime;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
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
