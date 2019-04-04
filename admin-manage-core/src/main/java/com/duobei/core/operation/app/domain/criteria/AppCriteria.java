package com.duobei.core.operation.app.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/4
 */
@Data
public class AppCriteria extends Pagination {
    private String productName;
    private String merchantName;
    private String appName;
}
