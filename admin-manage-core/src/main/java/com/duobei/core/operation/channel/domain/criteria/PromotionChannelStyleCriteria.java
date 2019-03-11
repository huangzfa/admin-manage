package com.duobei.core.operation.channel.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/5
 */
@Data
public class PromotionChannelStyleCriteria extends Pagination {

    private String styleName;

}
