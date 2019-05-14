package com.duobei.core.operation.app.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
@Data
public class AppUpgradeCriteria extends Pagination {
    private Integer productId;

    private Integer appId;

    private Integer versionNumber;



}
