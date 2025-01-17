package com.duobei.core.operation.channel.dao;

import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.channel.domain.criteria.AppMarketChannelCriteria;
import com.duobei.core.operation.channel.domain.criteria.ProductAppChannelCriteria;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelCriteria;
import com.duobei.core.operation.channel.domain.vo.ProductAppChannelListVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/5
 */
public interface PromotionChannelDao {
    PromotionChannel getById(Integer id);

    int countPromotionByQuery(PromotionChannelCriteria promotionChannelCriteria);

    List<PromotionChannel> getPromotionListByQuery(PromotionChannelCriteria appMarketChannelCriteria);

    int save(PromotionChannel promotionChannel);

    int update(PromotionChannel promotionChannel);

    int delete(PromotionChannel promotionChannel);

    int countAppMarketByQuery(AppMarketChannelCriteria appMarketChannelCriteria);

    List<PromotionChannel> getAppMarketListByQuery(AppMarketChannelCriteria appMarketChannelCriteria);

    PromotionChannel getByCode(String channelCode);

    List<ProductAppChannelListVo> getChannelByProductAppChannelQuery(ProductAppChannelCriteria productAppChannelCriteria);

    List<Integer> getIdListByName(String channelName);

    List<Integer> getAppChannelIdListByName(String channelName);

    List<PromotionChannel> getListByType(Integer type);
}
