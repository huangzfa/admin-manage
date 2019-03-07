package com.duobei.core.operation.product.dao;

import com.duobei.core.operation.product.domain.vo.ProductConsumdebtGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/7
 */
public interface ProductConsumdebtGoodsDao {

    List<ProductConsumdebtGoodsVo> getByProductId(@Param("productId") Integer productId);
}
