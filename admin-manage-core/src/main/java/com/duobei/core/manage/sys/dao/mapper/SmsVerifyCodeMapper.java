package com.duobei.core.manage.sys.dao.mapper;

import com.duobei.core.manage.sys.domain.SmsVerifyCode;
import com.duobei.core.manage.sys.domain.SmsVerifyCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsVerifyCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int countByExample(SmsVerifyCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int deleteByExample(SmsVerifyCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int insert(SmsVerifyCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int insertSelective(SmsVerifyCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    List<SmsVerifyCode> selectByExample(SmsVerifyCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    SmsVerifyCode selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int updateByExampleSelective(@Param("record") SmsVerifyCode record, @Param("example") SmsVerifyCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int updateByExample(@Param("record") SmsVerifyCode record, @Param("example") SmsVerifyCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int updateByPrimaryKeySelective(SmsVerifyCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_verify_code
     *
     * @mbg.generated Fri Nov 23 17:52:02 CST 2018
     */
    int updateByPrimaryKey(SmsVerifyCode record);
}