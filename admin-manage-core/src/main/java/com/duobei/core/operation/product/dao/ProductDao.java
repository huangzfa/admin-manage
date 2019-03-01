package com.duobei.core.operation.product.dao;


import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Repository
public interface ProductDao {
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    List<ProductVo> getPageList(ProductCriteria criteria);

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

    /**
     *
     * @param productIds
     * @return
     */
    List<Product> getByProductIds(@Param("productIds") List<Integer> productIds);

    /**
     * 查询code关联了哪些产品
     * @param code
     * @return
     */
    List<Product> getByAuthCode(@Param("code") String code);
}
