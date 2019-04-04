package com.duobei.core.operation.consume.dao;

import com.duobei.core.operation.consume.domain.ConsumeLoanRenewalConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
public interface ConsumeLoanRenewalConfigDao {
    List<ConsumeLoanRenewalConfig> getByConfigId(@Param("configId") Integer configId);
}
