package com.duobei.core.operation.biz.dao;

import com.duobei.core.operation.biz.domain.BizResource;
import org.apache.ibatis.annotations.Param;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface BizResourceDao {
    BizResource getByResTypeAnResTypeSec(@Param("resType") String resType ,@Param("resTypeSec") String resTypeSec);
}
