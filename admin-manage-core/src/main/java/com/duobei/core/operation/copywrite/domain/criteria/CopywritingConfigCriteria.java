package com.duobei.core.operation.copywrite.domain.criteria;

import com.duobei.common.util.Pagination;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/20
 */
public class CopywritingConfigCriteria extends Pagination {
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
