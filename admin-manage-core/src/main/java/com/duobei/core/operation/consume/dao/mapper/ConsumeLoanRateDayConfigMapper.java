package com.duobei.core.operation.consume.dao.mapper;

import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfig;
import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsumeLoanRateDayConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    long countByExample(ConsumeLoanRateDayConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int deleteByExample(ConsumeLoanRateDayConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int insert(ConsumeLoanRateDayConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int insertSelective(ConsumeLoanRateDayConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    List<ConsumeLoanRateDayConfig> selectByExample(ConsumeLoanRateDayConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    ConsumeLoanRateDayConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") ConsumeLoanRateDayConfig record, @Param("example") ConsumeLoanRateDayConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByExample(@Param("record") ConsumeLoanRateDayConfig record, @Param("example") ConsumeLoanRateDayConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByPrimaryKeySelective(ConsumeLoanRateDayConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByPrimaryKey(ConsumeLoanRateDayConfig record);
}