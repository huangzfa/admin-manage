package com.duobei.core.manage.auth.dao.mapper;

import com.duobei.core.manage.auth.domain.Organ;
import com.duobei.core.manage.auth.domain.OrganExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int countByExample(OrganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int deleteByExample(OrganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int deleteByPrimaryKey(Integer organId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int insert(Organ record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int insertSelective(Organ record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    List<Organ> selectByExample(OrganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    Organ selectByPrimaryKey(Integer organId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByExampleSelective(@Param("record") Organ record, @Param("example") OrganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByExample(@Param("record") Organ record, @Param("example") OrganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByPrimaryKeySelective(Organ record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByPrimaryKey(Organ record);
}