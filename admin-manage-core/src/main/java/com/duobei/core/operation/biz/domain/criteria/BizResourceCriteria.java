package com.duobei.core.operation.biz.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

@Data
public class BizResourceCriteria extends Pagination {

    private String name;

    private Integer productId;

    private Integer appId;
}
