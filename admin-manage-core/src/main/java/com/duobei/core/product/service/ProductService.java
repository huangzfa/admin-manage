package com.duobei.core.product.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.product.domain.Product;
import com.duobei.core.product.domain.criteria.ProductCriteria;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface ProductService {
    /**
     * 查询产品列表
     * @param criteria
     * @return
     */
    ListVo<Product> getLists(ProductCriteria criteria);

    /**
     * 根据主键查询产品
     * @param id
     * @return
     */
    Product getById(Integer id);

    /**
     * 根据产品编码查询产品
     * @param code
     * @return
     */
    Product getByCode(String code);

    /**
     * 禁用启用产品
     * @param record
     * @return
     */
    int editState(Product record);
}