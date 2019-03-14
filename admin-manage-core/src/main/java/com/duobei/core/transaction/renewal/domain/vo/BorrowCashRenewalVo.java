package com.duobei.core.transaction.renewal.domain.vo;

import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
public class BorrowCashRenewalVo extends BorrowCashRenewal {

    private BorrowCash borrowCash;

    public BorrowCash getBorrowCash() {
        return borrowCash;
    }

    public void setBorrowCash(BorrowCash borrowCash) {
        this.borrowCash = borrowCash;
    }
}
