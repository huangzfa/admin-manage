package com.duobei.core.product.dao;

import com.duobei.core.product.domain.Product;
import com.duobei.core.product.domain.criteria.ProductCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface ProductDao {
    List<Product> getLists(ProductCriteria criteria);

    int countByCriteria(ProductCriteria criteria);

    Product getById(Integer id);

    Product getByCode(@Param("code") String code);

    int update(Product record);

    int save(Product record);
}
