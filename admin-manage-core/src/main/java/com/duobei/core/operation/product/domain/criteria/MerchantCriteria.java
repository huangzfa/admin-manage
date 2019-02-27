package com.duobei.core.operation.product.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@Data
public class MerchantCriteria extends Pagination {

    private String productName;

    private String merchantName;

    private Integer merchantId;

}
