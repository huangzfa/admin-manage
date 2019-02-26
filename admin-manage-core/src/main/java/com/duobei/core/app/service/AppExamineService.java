package com.duobei.core.app.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.app.domain.AppExamine;
import com.duobei.core.app.domain.criteria.AppExamineCriteria;

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
    ListVo<AppExamine> getPageList(AppExamineCriteria appExamineCriteria);
}
