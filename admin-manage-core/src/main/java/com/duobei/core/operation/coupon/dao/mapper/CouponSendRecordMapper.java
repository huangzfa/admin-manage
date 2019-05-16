package com.duobei.core.operation.coupon.dao.mapper;

import com.duobei.core.operation.coupon.domain.CouponSendRecord;
import com.duobei.core.operation.coupon.domain.CouponSendRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponSendRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    long countByExample(CouponSendRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int deleteByExample(CouponSendRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int insert(CouponSendRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int insertSelective(CouponSendRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    List<CouponSendRecord> selectByExample(CouponSendRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    CouponSendRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int updateByExampleSelective(@Param("record") CouponSendRecord record, @Param("example") CouponSendRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int updateByExample(@Param("record") CouponSendRecord record, @Param("example") CouponSendRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int updateByPrimaryKeySelective(CouponSendRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_coupon_send_record
     *
     * @mbg.generated Fri May 10 11:56:13 CST 2019
     */
    int updateByPrimaryKey(CouponSendRecord record);
}