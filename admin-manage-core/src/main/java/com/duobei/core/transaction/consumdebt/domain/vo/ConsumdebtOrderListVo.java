package com.duobei.core.transaction.consumdebt.domain.vo;

import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/8
 */
public class ConsumdebtOrderListVo extends ConsumdebtOrder {
    private String productName;

    private String borrowNo;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
    }
}
