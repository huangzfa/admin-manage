package com.duobei.core.operation.product.service;

import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface ProductAuthConfigService {

    /**
     * 根据产品di查询认证项配置
     * @param productId
     * @return
     */
    List<ProductAuthConfigVo> getByProductId(Integer productId);
}
