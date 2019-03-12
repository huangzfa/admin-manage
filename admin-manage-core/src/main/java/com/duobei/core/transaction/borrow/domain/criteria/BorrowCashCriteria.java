package com.duobei.core.transaction.borrow.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
@Data
public class BorrowCashCriteria extends Pagination {
    private String borrowNo;

    private String mobile;

    private Integer productId;

}
