package com.duobei.core.operation.app.service;

import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.product.domain.Product;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface AppService {
    /**
     * 根据产品查询app
     * @param list
     * @return
     */
    List<App> getByProductIds(List<Product> list);
}
