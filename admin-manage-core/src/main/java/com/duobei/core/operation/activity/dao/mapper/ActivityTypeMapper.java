package com.duobei.core.operation.activity.dao.mapper;

import com.duobei.core.operation.activity.domain.ActivityType;
import com.duobei.core.operation.activity.domain.ActivityTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    long countByExample(ActivityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int deleteByExample(ActivityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int deleteByPrimaryKey(Integer atId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int insert(ActivityType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int insertSelective(ActivityType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    List<ActivityType> selectByExample(ActivityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    ActivityType selectByPrimaryKey(Integer atId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") ActivityType record, @Param("example") ActivityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int updateByExample(@Param("record") ActivityType record, @Param("example") ActivityTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int updateByPrimaryKeySelective(ActivityType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Thu Apr 11 15:34:00 CST 2019
     */
    int updateByPrimaryKey(ActivityType record);
}