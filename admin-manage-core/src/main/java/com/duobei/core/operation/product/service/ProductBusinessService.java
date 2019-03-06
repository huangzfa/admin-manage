package com.duobei.core.operation.product.service;

import com.duobei.core.operation.product.domain.ProductBusiness;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface ProductBusinessService {
    List<ProductBusiness> getByProductId(Integer productId);
}
