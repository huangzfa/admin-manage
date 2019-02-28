package com.duobei.core.operation.app.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.criteria.AppExamineCriteria;

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
    ListVo<AppExamine> getPageList(AppExamineCriteria appExamineCriteria) ;
}
