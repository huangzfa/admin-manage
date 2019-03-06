package com.duobei.core.operation.consume.service;

import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfig;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
public interface ConsumeLoanRateDayConfigService {

    /**
     * 根据消费贷配置id，查询利率区间配置
     * @param loanConfigId
     * @return
     */
    List<ConsumeLoanRateDayConfig> getByLoanConfigId(Integer loanConfigId);
}
