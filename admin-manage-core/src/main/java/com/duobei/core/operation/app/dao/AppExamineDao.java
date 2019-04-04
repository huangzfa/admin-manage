package com.duobei.core.operation.app.dao;


import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.AppExamineExample;
import com.duobei.core.operation.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.operation.app.domain.vo.AppExamineVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface AppExamineDao {

    /**
     * 总条数
     * @param criteria
     * @return
     */
    long countCriteria (AppExamineCriteria criteria);

    /**
     * list数据
     * @param criteria
     * @return
     */
    List getPageList(AppExamineCriteria criteria);

    int save(AppExamine appExamine);

    /**
     * 根据条件查询
     * @param criteria
     * @return
     */
    List<AppExamineVo> getListVoByQuery(AppExamineExample.Criteria criteria);

    int delete(AppExamine appExamine);
}
