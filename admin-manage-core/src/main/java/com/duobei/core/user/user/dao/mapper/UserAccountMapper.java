package com.duobei.core.user.user.dao.mapper;

import com.duobei.core.user.user.domain.UserAccount;
import com.duobei.core.user.user.domain.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    long countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    UserAccount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_account
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    int updateByPrimaryKey(UserAccount record);
}