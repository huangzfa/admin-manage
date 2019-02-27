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
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    List<Product> getPageList(ProductCriteria criteria);

    /**
     * 数量查询
     * @param criteria
     * @return
     */
    int countByCriteria(ProductCriteria criteria);

    /**
     * 查询全部启用产品
     * @return
     */
    List<Product> getAll();

    /**
     *
     * @param id
     * @return
     */
    Product getById(Integer id);

    /**
     *
     * @param code
     * @return
     */
    Product getByCode(@Param("code") String code);

    /**
     *
     * @param record
     * @return
     */
    int update(Product record);

    /**
     *
     * @param record
     * @return
     */
    int save(Product record);
}
