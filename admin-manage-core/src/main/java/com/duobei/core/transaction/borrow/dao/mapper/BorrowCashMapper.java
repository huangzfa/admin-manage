package com.duobei.core.transaction.borrow.dao.mapper;

import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.BorrowCashExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowCashMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    long countByExample(BorrowCashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int deleteByExample(BorrowCashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int insert(BorrowCash record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int insertSelective(BorrowCash record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    List<BorrowCash> selectByExample(BorrowCashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    BorrowCash selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int updateByExampleSelective(@Param("record") BorrowCash record, @Param("example") BorrowCashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int updateByExample(@Param("record") BorrowCash record, @Param("example") BorrowCashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int updateByPrimaryKeySelective(BorrowCash record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_borrow_cash
     *
     * @mbg.generated Tue Mar 12 17:15:45 CST 2019
     */
    int updateByPrimaryKey(BorrowCash record);
}