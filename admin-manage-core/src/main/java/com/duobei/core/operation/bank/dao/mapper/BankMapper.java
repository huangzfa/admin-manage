package com.duobei.core.operation.bank.dao.mapper;

import com.duobei.core.operation.bank.domain.Bank;
import com.duobei.core.operation.bank.domain.BankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    long countByExample(BankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int deleteByExample(BankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int insert(Bank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int insertSelective(Bank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    List<Bank> selectByExample(BankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    Bank selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int updateByExampleSelective(@Param("record") Bank record, @Param("example") BankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int updateByExample(@Param("record") Bank record, @Param("example") BankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int updateByPrimaryKeySelective(Bank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_bank
     *
     * @mbg.generated Tue Mar 19 11:59:59 CST 2019
     */
    int updateByPrimaryKey(Bank record);
}