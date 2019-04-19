package com.duobei.core.operation.zfb.domain.criteria;

import com.duobei.common.util.Pagination;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public class ZfbAccountCriteria extends Pagination {
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
