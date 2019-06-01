package com.duobei.core.operation.product.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Data
public class ProductCriteria extends Pagination {

    private String productName;

    private Integer merchantId;

    private String merchantName;

    private String productCode;
    /**
     * 服务类型
     */
    private String serviceCode;

    private List<Integer> productList;

}
