package com.duobei.core.manage.auth.dao.mapper;

import com.duobei.core.manage.auth.domain.RoleDataAuth;
import com.duobei.core.manage.auth.domain.RoleDataAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleDataAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    long countByExample(RoleDataAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int deleteByExample(RoleDataAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int insert(RoleDataAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int insertSelective(RoleDataAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    List<RoleDataAuth> selectByExample(RoleDataAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    RoleDataAuth selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int updateByExampleSelective(@Param("record") RoleDataAuth record, @Param("example") RoleDataAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int updateByExample(@Param("record") RoleDataAuth record, @Param("example") RoleDataAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int updateByPrimaryKeySelective(RoleDataAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_data_auth
     *
     * @mbg.generated Wed Feb 27 14:49:39 CST 2019
     */
    int updateByPrimaryKey(RoleDataAuth record);
}