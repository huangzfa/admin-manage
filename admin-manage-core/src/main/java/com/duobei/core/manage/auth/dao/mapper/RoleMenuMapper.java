package com.duobei.core.manage.auth.dao.mapper;

import com.duobei.core.manage.auth.domain.RoleMenuExample;
import com.duobei.core.manage.auth.domain.RoleMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int countByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int deleteByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int deleteByPrimaryKey(RoleMenuKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int insert(RoleMenuKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int insertSelective(RoleMenuKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    List<RoleMenuKey> selectByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByExampleSelective(@Param("record") RoleMenuKey record, @Param("example") RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_role_menu
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByExample(@Param("record") RoleMenuKey record, @Param("example") RoleMenuExample example);
}