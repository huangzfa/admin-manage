package com.duobei.core.operation.product.dao;


import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    List<Product> getByMerchantId(@Param("merchantId") Integer productId);

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
    int update(ProductVo record);

    /**
     *
     * @param record
     * @return
     */
    int save(ProductVo record);

    /**
     *
     * @param productIds
     * @return
     */
    List<Product> getByProductIds(@Param("productIds") List<Integer> productIds);

    /**
     * 查询code关联了哪些产品
     * @param authId
     * @return
     */
    List<Product> getByAuthId(@Param("authId") Integer authId);

    @MapKey("id")
    Map<Integer,Product> getMapProduct();

}
