package com.duobei.core.operation.product.dao;


import com.duobei.core.operation.product.domain.ProductBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 产品业务表
 * @date 2019/2/26
 */
public interface ProductBusinessDao {
    /**
     *
     * @param productId
     * @return
     */
    List<ProductBusiness> getByProductId(@Param("productId") Integer productId);

    /**
     *
     * @param productId
     */
    void updateState(@Param("productId") Integer productId,@Param("state") Integer state);

    int save(ProductBusiness entity);

    int update(ProductBusiness entity);

    ProductBusiness getByBizCode(ProductBusiness entity);

}
