package com.duobei.core.operation.activity.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
@Data
public class ActivityCriteria extends Pagination {
    private Integer productId;
}
