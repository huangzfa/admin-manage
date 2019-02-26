package com.duobei.core.product.dao.mapper;

import com.duobei.core.product.domain.Product;
import com.duobei.core.product.domain.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    long countByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int deleteByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product
     *
     * @mbg.generated Tue Feb 26 17:32:55 CST 2019
     */
    int updateByPrimaryKey(Product record);
}