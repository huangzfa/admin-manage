package com.duobei.core.operation.channel.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.channel.domain.ProductAppChannel;
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

    /**
     * 根据渠道id跟应用id进行查询
     * @param channelId
     * @param appId
     * @return
     */
    ProductAppChannel getByChannelAndAppId(Integer channelId, Integer appId);

    /**
     * 新增H5注册页链接配置
     * @param productAppChannel
     */
    void save(ProductAppChannel productAppChannel) throws TqException;

    /**
     * 修改H5注册页链接配置
     * @param productAppChannel
     */
    void update(ProductAppChannel productAppChannel) throws TqException;
}
