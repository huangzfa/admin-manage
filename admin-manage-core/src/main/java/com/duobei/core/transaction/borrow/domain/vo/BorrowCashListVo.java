package com.duobei.core.transaction.borrow.domain.vo;

import com.duobei.core.transaction.borrow.domain.BorrowCash;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
@Data
public class BorrowCashListVo extends BorrowCash {
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 用户手机号
     */
    private String userName;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 平账状态
     */
    private Integer offlineState;

    /**
     * 待还金额
     */

    private Long repaymentAmount;
}
