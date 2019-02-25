package com.duobei.core.auth.dao;

import com.duobei.core.auth.domain.OperatorLoginLog;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangzhongfa
 * @description
 * @date 2018/12/26
 */
public interface OperatorLoginLogDao {

    OperatorLoginLog getByOpId(@Param("opId") Integer opId);
}
