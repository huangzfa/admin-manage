package com.duobei.core.operation.banner.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/1
 */
@Data
public class BannerCriteria extends Pagination {
    private Integer appId;

    private String bannerType;

    private String titleName;

    private Integer isEnable;
}
