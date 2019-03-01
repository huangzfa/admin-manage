package com.duobei.core.operation.product.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Data
public class ProductCriteria extends Pagination {

    private String productName;

    private Integer merchantId;

}
