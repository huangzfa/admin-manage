package com.duobei.core.transaction.repayment.domain.vo;

import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
public class BorrowCashRepaymentVo extends BorrowCashRepayment {
    private BorrowCash borrowCash;

    public BorrowCash getBorrowCash() {
        return borrowCash;
    }

    public void setBorrowCash(BorrowCash borrowCash) {
        this.borrowCash = borrowCash;
    }
}
