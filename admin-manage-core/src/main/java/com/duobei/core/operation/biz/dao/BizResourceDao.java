package com.duobei.core.operation.biz.dao;

import com.duobei.core.operation.biz.domain.BizResource;
import com.duobei.core.operation.biz.domain.criteria.BizResourceCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface BizResourceDao {
    BizResource getByResTypeAnResTypeSec(@Param("resType") String resType ,@Param("resTypeSec") String resTypeSec);

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    List<BizResource> getPage(BizResourceCriteria criteria);

    int countByCriteria(BizResourceCriteria criteria);

    BizResource getById(Integer id);

    int save(BizResource entity);

    int update(BizResource entity);

    int countByType(HashMap<String,Object> map);
}
