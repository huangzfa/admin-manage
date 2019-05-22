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

    /**
     * 服务化订单号
     */
    private String borrowNo;

    /**
     * 注册手机号
     */
    private String userName;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 用户id
     */
    private Long userId;


    private Integer borrowState;

}
