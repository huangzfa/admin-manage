package com.duobei.core.operation.consume.dao;

import com.duobei.core.operation.consume.domain.ConsumeLoanRenewalConfig;
import com.duobei.core.operation.consume.domain.vo.ConsumeLoanRenewalConfigVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
public interface ConsumeLoanRenewalConfigDao {
    /**
     *
     * @param configId
     * @return
     */
    List<ConsumeLoanRenewalConfigVo> getByConfigId(@Param("configId") Integer configId);

    int save (ConsumeLoanRenewalConfig renewalConfig);

    int update (ConsumeLoanRenewalConfig renewalConfig);
}
