package com.duobei.core.manage.auth.dao.mapper;

import com.duobei.core.manage.auth.domain.VerifyCodeFail;
import com.duobei.core.manage.auth.domain.VerifyCodeFailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VerifyCodeFailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    long countByExample(VerifyCodeFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int deleteByExample(VerifyCodeFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int insert(VerifyCodeFail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int insertSelective(VerifyCodeFail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    List<VerifyCodeFail> selectByExample(VerifyCodeFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    VerifyCodeFail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int updateByExampleSelective(@Param("record") VerifyCodeFail record, @Param("example") VerifyCodeFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int updateByExample(@Param("record") VerifyCodeFail record, @Param("example") VerifyCodeFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int updateByPrimaryKeySelective(VerifyCodeFail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_verify_code_fail
     *
     * @mbg.generated Wed Dec 26 14:39:54 CST 2018
     */
    int updateByPrimaryKey(VerifyCodeFail record);
}