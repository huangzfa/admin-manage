package com.duobei.core.operation.app.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.operation.app.domain.vo.AppExamineVo;


/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface AppExamineService {

    /**
     * @param appExamineCriteria
     * @return
     */
    ListVo<AppExamineVo> getListVoByQuery(AppExamineCriteria appExamineCriteria) throws TqException;

    AppExamine getById(Integer id);

    void delete(AppExamine appExamine) throws TqException;

    void save(AppExamine entity) throws TqException;

    void update(AppExamine entity) throws TqException;
}
