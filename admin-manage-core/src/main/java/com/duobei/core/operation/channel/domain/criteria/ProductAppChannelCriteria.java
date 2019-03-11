package com.duobei.core.operation.channel.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/11
 */
@Data
public class ProductAppChannelCriteria extends Pagination {
    private Integer appId;

    private String channelName;

    private String channelCode;

}
