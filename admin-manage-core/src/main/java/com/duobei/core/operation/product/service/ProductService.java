package com.duobei.core.operation.product.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.ProductVo;

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
    ListVo<ProductVo> getLists(ProductCriteria criteria);

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
    void editState(Product record) throws TqException;

    /**
     * 修改产品
     * @param product
     */
    void update(Product product) throws TqException;

    /**
     * 添加产品
     * @param product
     */
    void save(Product product) throws TqException;


}
