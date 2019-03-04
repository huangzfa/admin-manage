package com.duobei.core.operation.consume.dao.mapper;

import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsumeLoanConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    long countByExample(ConsumeLoanConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int deleteByExample(ConsumeLoanConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int insert(ConsumeLoanConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int insertSelective(ConsumeLoanConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    List<ConsumeLoanConfig> selectByExample(ConsumeLoanConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    ConsumeLoanConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") ConsumeLoanConfig record, @Param("example") ConsumeLoanConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByExample(@Param("record") ConsumeLoanConfig record, @Param("example") ConsumeLoanConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByPrimaryKeySelective(ConsumeLoanConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByPrimaryKey(ConsumeLoanConfig record);
}