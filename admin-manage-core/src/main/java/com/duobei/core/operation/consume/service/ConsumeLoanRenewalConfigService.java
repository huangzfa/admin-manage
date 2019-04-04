package com.duobei.core.operation.consume.service;

import com.duobei.core.operation.consume.domain.ConsumeLoanRenewalConfig;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/4
 */
public interface ConsumeLoanRenewalConfigService {
    /**
     * 根据消费贷配置id查询利率天数配置
     * @param configId
     * @return
     */
    List<ConsumeLoanRenewalConfig> getByConfigId(Integer configId);
}
