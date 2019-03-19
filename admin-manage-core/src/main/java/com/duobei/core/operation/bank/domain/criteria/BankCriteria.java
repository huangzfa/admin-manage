package com.duobei.core.operation.bank.domain.criteria;

import com.duobei.common.util.Pagination;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public class BankCriteria extends Pagination {
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
