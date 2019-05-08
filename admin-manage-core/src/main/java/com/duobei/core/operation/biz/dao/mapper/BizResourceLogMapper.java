package com.duobei.core.operation.biz.dao.mapper;

import com.duobei.core.operation.biz.domain.BizResourceLog;
import com.duobei.core.operation.biz.domain.BizResourceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizResourceLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    long countByExample(BizResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int deleteByExample(BizResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int insert(BizResourceLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int insertSelective(BizResourceLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    List<BizResourceLog> selectByExample(BizResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    BizResourceLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int updateByExampleSelective(@Param("record") BizResourceLog record, @Param("example") BizResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int updateByExample(@Param("record") BizResourceLog record, @Param("example") BizResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int updateByPrimaryKeySelective(BizResourceLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_biz_resource_log
     *
     * @mbg.generated Wed May 08 10:22:26 CST 2019
     */
    int updateByPrimaryKey(BizResourceLog record);
}