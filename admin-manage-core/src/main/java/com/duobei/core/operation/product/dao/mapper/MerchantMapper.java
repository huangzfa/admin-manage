package com.duobei.core.operation.product.dao.mapper;

import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.MerchantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface MerchantMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    long countByExample(MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int deleteByExample(MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int insert(Merchant record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int insertSelective(Merchant record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    List<Merchant> selectByExample(MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    Merchant selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int updateByExampleSelective(@Param("record") Merchant record, @Param("example") MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int updateByExample(@Param("record") Merchant record, @Param("example") MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int updateByPrimaryKeySelective(Merchant record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_merchant
     *
     * @mbg.generated Wed Feb 27 11:03:29 CST 2019
     */
    int updateByPrimaryKey(Merchant record);
}