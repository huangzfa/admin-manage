package com.duobei.core.message.sms.dao;

import com.duobei.core.message.sms.domain.SmsTemplet;
import com.duobei.core.message.sms.domain.criteria.SmsTempletCriteria;
import com.duobei.core.message.sms.domain.vo.SmsTempletVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/15
 */
public interface SmsTempletDao {

    List<SmsTempletVo> getPageList(SmsTempletCriteria criteria);

    int countByCriteria(SmsTempletCriteria criteria);

    int countByType(SmsTemplet record);

    SmsTemplet getById(Long id);

    int save(SmsTemplet record);

    int update(SmsTemplet record);

    SmsTemplet getByCode(@Param("code") String code);

}
