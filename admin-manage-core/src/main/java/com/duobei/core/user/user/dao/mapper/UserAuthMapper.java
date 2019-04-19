package com.duobei.core.user.user.dao.mapper;

import com.duobei.core.user.user.domain.UserAuth;
import com.duobei.core.user.user.domain.UserAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    long countByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int deleteByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int insert(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int insertSelective(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    List<UserAuth> selectByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    UserAuth selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByExample(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByPrimaryKeySelective(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_auth
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByPrimaryKey(UserAuth record);
}