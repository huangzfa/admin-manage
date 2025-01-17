package com.duobei.core.operation.app.dao.mapper;

import com.duobei.core.operation.app.domain.AppUpgrade;
import com.duobei.core.operation.app.domain.AppUpgradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppUpgradeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    long countByExample(AppUpgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int deleteByExample(AppUpgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int insert(AppUpgrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int insertSelective(AppUpgrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    List<AppUpgrade> selectByExampleWithBLOBs(AppUpgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    List<AppUpgrade> selectByExample(AppUpgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    AppUpgrade selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") AppUpgrade record, @Param("example") AppUpgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") AppUpgrade record, @Param("example") AppUpgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int updateByExample(@Param("record") AppUpgrade record, @Param("example") AppUpgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int updateByPrimaryKeySelective(AppUpgrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(AppUpgrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_app_upgrade
     *
     * @mbg.generated Wed Apr 10 14:56:21 CST 2019
     */
    int updateByPrimaryKey(AppUpgrade record);
}