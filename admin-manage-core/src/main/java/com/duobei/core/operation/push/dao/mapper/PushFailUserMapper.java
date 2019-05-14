package com.duobei.core.operation.push.dao.mapper;

import com.duobei.core.operation.push.domain.PushFailUser;
import com.duobei.core.operation.push.domain.PushFailUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushFailUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    long countByExample(PushFailUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int deleteByExample(PushFailUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int insert(PushFailUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int insertSelective(PushFailUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    List<PushFailUser> selectByExample(PushFailUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    PushFailUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByExampleSelective(@Param("record") PushFailUser record, @Param("example") PushFailUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByExample(@Param("record") PushFailUser record, @Param("example") PushFailUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByPrimaryKeySelective(PushFailUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_push_fail_user
     *
     * @mbg.generated Mon May 13 16:52:32 CST 2019
     */
    int updateByPrimaryKey(PushFailUser record);
}