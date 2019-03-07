package com.duobei.core.operation.product.service;

import com.duobei.core.operation.product.domain.vo.ProductConsumdebtGoodsVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/7
 */
public interface ProductConsumdebtGoodsService {
    /**
     * 根据产品id查询关联的商品
     * @param productId
     * @return
     */
    List<ProductConsumdebtGoodsVo> getByProductId(Integer productId);
}
