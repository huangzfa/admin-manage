package com.duobei.core.operation.product.dao.mapper;

import com.duobei.core.operation.product.domain.Service;
import com.duobei.core.operation.product.domain.ServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    long countByExample(ServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int deleteByExample(ServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int deleteByPrimaryKey(String serviceCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int insert(Service record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int insertSelective(Service record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    List<Service> selectByExample(ServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    Service selectByPrimaryKey(String serviceCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByExampleSelective(@Param("record") Service record, @Param("example") ServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByExample(@Param("record") Service record, @Param("example") ServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByPrimaryKeySelective(Service record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_service
     *
     * @mbg.generated Wed Apr 24 18:22:48 CST 2019
     */
    int updateByPrimaryKey(Service record);
}