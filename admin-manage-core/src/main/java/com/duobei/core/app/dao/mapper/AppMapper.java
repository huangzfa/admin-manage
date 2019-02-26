package com.duobei.core.app.dao.mapper;

import com.duobei.core.app.domain.App;
import com.duobei.core.app.domain.AppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    long countByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int deleteByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int insert(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int insertSelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    List<App> selectByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    App selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app
     *
     * @mbg.generated Tue Feb 26 10:03:56 CST 2019
     */
    int updateByPrimaryKey(App record);
}