package com.duobei.core.operation.push.dao;

import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.criteria.QuartzInfoCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
public interface QuartzInfoDao {

    List<QuartzInfo> getPage(QuartzInfoCriteria criteria);

    int countByCriteria(QuartzInfoCriteria criteria);

    int save(QuartzInfo record);

    QuartzInfo getByCode(@Param("code") String code);

    int update(QuartzInfo record);
}
