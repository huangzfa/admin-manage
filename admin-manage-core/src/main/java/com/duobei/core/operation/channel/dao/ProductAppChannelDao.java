package com.duobei.core.operation.channel.dao;


import com.duobei.core.operation.channel.domain.ProductAppChannel;
import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import com.duobei.core.operation.channel.domain.vo.ChannelStyleCountVo;
import com.duobei.core.operation.channel.domain.vo.ProductAppChannelListVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author huangzhongfa
 * @description 产品应用推广渠道信息
 * @date 2019/2/26
 */
public interface ProductAppChannelDao{

    @MapKey("channelStyleId")
    Map<Integer,ChannelStyleCountVo> getCountMapByGroup();

    Integer getCountByStyleId(Integer id);

    @MapKey("channelId")
    Map<Integer,ProductAppChannel> getListByChannelIdAndAppId(@Param("data") List<ProductAppChannelListVo> data,@Param("appId") Integer appId);

    ProductAppChannel getByChannelAndAppId(@Param("channelId") Integer channelId, @Param("appId") Integer appId);

    int save(ProductAppChannel productAppChannel);

    int update(ProductAppChannel productAppChannel);
}