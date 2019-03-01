package com.duobei.core.operation.app.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/2/28
 */
@Data
public class AppPageConfigCriteria extends Pagination {
    private Integer appId;
}
