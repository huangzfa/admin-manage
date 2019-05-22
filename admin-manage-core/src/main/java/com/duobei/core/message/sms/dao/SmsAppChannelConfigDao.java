package com.duobei.core.message.sms.dao;

import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import com.duobei.core.message.sms.domain.criteria.SmsAppChannelConfigCriteria;
import com.duobei.core.message.sms.domain.vo.SmsAppChannelConfigVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
public interface SmsAppChannelConfigDao {

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    List<SmsAppChannelConfigVo> getPageList(SmsAppChannelConfigCriteria criteria);

    /**
     * 数量查询
     * @param criteria
     * @return
     */
    int countByCriteria(SmsAppChannelConfigCriteria criteria);

    SmsAppChannelConfig getById(Long id);

    int save(SmsAppChannelConfig record);

    int update(SmsAppChannelConfig record);

    List<SmsAppChannelConfig> getByAppkey(@Param("appKey") String appKey);

    int countByAppKey(SmsAppChannelConfig config);

    SmsAppChannelConfig getByRecord(SmsAppChannelConfig config);

}
