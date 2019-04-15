package com.duobei.core.operation.activity.dao.mapper;

import com.duobei.core.operation.activity.domain.ActivityStatic;
import com.duobei.core.operation.activity.domain.ActivityStaticExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityStaticMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    long countByExample(ActivityStaticExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    int deleteByExample(ActivityStaticExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    int insert(ActivityStatic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    int insertSelective(ActivityStatic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    List<ActivityStatic> selectByExample(ActivityStaticExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    int updateByExampleSelective(@Param("record") ActivityStatic record, @Param("example") ActivityStaticExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    int updateByExample(@Param("record") ActivityStatic record, @Param("example") ActivityStaticExample example);
}