package com.duobei.core.message.sms.dao.mapper;

import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import com.duobei.core.message.sms.domain.SmsAppChannelConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsAppChannelConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    long countByExample(SmsAppChannelConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int deleteByExample(SmsAppChannelConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int insert(SmsAppChannelConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int insertSelective(SmsAppChannelConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    List<SmsAppChannelConfig> selectByExample(SmsAppChannelConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    SmsAppChannelConfig selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int updateByExampleSelective(@Param("record") SmsAppChannelConfig record, @Param("example") SmsAppChannelConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int updateByExample(@Param("record") SmsAppChannelConfig record, @Param("example") SmsAppChannelConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int updateByPrimaryKeySelective(SmsAppChannelConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_sms_channel_config
     *
     * @mbg.generated Tue May 14 16:08:57 CST 2019
     */
    int updateByPrimaryKey(SmsAppChannelConfig record);
}