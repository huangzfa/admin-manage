package com.duobei.core.operation.channel.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.channel.domain.criteria.AppMarketChannelCriteria;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelCriteria;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/5
 */
public interface PromotionChannelService {
    /**
     * 根据ID获取渠道信息
     * @param id
     * @return
     */
    PromotionChannel getById(Integer id);

    /**
     * 根据查询条件获取推广渠道列表
     * @param promotionChannelCriteria
     * @return
     */
    ListVo<PromotionChannel> getPromotionListByQuery(PromotionChannelCriteria promotionChannelCriteria);

    /**
     * 新增渠道
     * @param promotionChannel
     */
    void addPromotionChannel(PromotionChannel promotionChannel) throws TqException;

    /**
     * 修改渠道
     * @param promotionChannel
     */
    void updatePromotionChannel(PromotionChannel promotionChannel) throws TqException;

    /**
     * 删除渠道
     * @param promotionChannel
     */
    void delete(PromotionChannel promotionChannel) throws TqException;

    /**
     * 获取应用市场列表
     * @param appMarketChannelCriteria
     * @return
     */
    ListVo<PromotionChannel> getAppMarketListByQuery(AppMarketChannelCriteria appMarketChannelCriteria);
}
