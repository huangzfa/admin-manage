package com.duobei.core.operation.channel.dao;

import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.channel.domain.criteria.AppMarketChannelCriteria;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelCriteria;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/5
 */
public interface PromotionChannelDao {
    PromotionChannel getById(Integer id);

    int countPromotionByQuery(PromotionChannelCriteria promotionChannelCriteria);

    List<PromotionChannel> getPromotionListByQuery();

    int save(PromotionChannel promotionChannel);

    int update(PromotionChannel promotionChannel);

    int delete(PromotionChannel promotionChannel);

    int countAppMarketByQuery(AppMarketChannelCriteria appMarketChannelCriteria);

    List<PromotionChannel> getAppMarketListByQuery(AppMarketChannelCriteria appMarketChannelCriteria);
}
