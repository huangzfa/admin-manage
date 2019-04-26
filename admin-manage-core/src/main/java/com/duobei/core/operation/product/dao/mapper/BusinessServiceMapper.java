package com.duobei.core.operation.product.dao.mapper;

import com.duobei.core.operation.product.domain.BusinessService;
import com.duobei.core.operation.product.domain.BusinessServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessServiceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    long countByExample(BusinessServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int deleteByExample(BusinessServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int insert(BusinessService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int insertSelective(BusinessService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    List<BusinessService> selectByExample(BusinessServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    BusinessService selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByExampleSelective(@Param("record") BusinessService record, @Param("example") BusinessServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByExample(@Param("record") BusinessService record, @Param("example") BusinessServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByPrimaryKeySelective(BusinessService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_business_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByPrimaryKey(BusinessService record);
}