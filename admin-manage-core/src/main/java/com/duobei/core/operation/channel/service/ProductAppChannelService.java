package com.duobei.core.operation.channel.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.channel.domain.criteria.ProductAppChannelCriteria;
import com.duobei.core.operation.channel.domain.vo.ProductAppChannelListVo;

import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/2/26
 */
public interface ProductAppChannelService {
    /**
     * 根据查询条件查询列表
     * @param criteria
     * @return
     */
    ListVo<ProductAppChannelListVo> getListByQuery(ProductAppChannelCriteria criteria);
}
