package com.duobei.core.manage.auth.dao.mapper;

import com.duobei.core.manage.auth.domain.OperatorLoginLog;
import com.duobei.core.manage.auth.domain.OperatorLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperatorLoginLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int countByExample(OperatorLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int deleteByExample(OperatorLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int insert(OperatorLoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int insertSelective(OperatorLoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    List<OperatorLoginLog> selectByExample(OperatorLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    OperatorLoginLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByExampleSelective(@Param("record") OperatorLoginLog record, @Param("example") OperatorLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByExample(@Param("record") OperatorLoginLog record, @Param("example") OperatorLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByPrimaryKeySelective(OperatorLoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_operator_login_log
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    int updateByPrimaryKey(OperatorLoginLog record);
}