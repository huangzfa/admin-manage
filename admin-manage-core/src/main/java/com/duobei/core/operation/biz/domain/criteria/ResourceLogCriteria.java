package com.duobei.core.operation.biz.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

@Data
public class ResourceLogCriteria extends Pagination {

    private String resType;

}
