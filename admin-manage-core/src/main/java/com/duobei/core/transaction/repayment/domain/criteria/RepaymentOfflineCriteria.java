package com.duobei.core.transaction.repayment.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/20
 */

@Data
public class RepaymentOfflineCriteria extends Pagination {

    private Integer userId;

    private Integer productId;

    private Integer state;

}
