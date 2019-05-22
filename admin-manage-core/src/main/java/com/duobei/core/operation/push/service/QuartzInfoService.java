package com.duobei.core.operation.push.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.domain.criteria.QuartzInfoCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/15
 */
public interface QuartzInfoService {

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    ListVo<QuartzInfo> getPageList(QuartzInfoCriteria criteria);

    QuartzInfo getByCode(String code);

    void update(QuartzInfo record) throws Exception;

    void save(QuartzInfo record) throws Exception;

    void delete(QuartzInfo record) throws Exception;

    void editState(QuartzInfo record) throws Exception;

    List<QuartzInfo> getStart();

}
