package com.duobei.core.transaction.renewal.dao.mapper;

import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;
import com.duobei.core.transaction.renewal.domain.BorrowCashRenewalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowCashRenewalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    long countByExample(BorrowCashRenewalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int deleteByExample(BorrowCashRenewalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int insert(BorrowCashRenewal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int insertSelective(BorrowCashRenewal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    List<BorrowCashRenewal> selectByExample(BorrowCashRenewalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    BorrowCashRenewal selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int updateByExampleSelective(@Param("record") BorrowCashRenewal record, @Param("example") BorrowCashRenewalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int updateByExample(@Param("record") BorrowCashRenewal record, @Param("example") BorrowCashRenewalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int updateByPrimaryKeySelective(BorrowCashRenewal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash_renewal
     *
     * @mbg.generated Thu Mar 14 17:36:16 CST 2019
     */
    int updateByPrimaryKey(BorrowCashRenewal record);
}