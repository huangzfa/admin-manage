package com.duobei.core.operation.push.dao.mapper;

import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.PushRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    long countByExample(PushRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int deleteByExample(PushRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int insert(PushRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int insertSelective(PushRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    List<PushRecord> selectByExample(PushRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    PushRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByExampleSelective(@Param("record") PushRecord record, @Param("example") PushRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByExample(@Param("record") PushRecord record, @Param("example") PushRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByPrimaryKeySelective(PushRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_record
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByPrimaryKey(PushRecord record);
}