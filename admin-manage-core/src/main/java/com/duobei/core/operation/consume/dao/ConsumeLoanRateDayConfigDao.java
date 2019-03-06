package com.duobei.core.operation.consume.dao;

import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
public interface ConsumeLoanRateDayConfigDao {

    /**
     * 根据消费贷配置id，查询利率区间配置
     * @param loanConfigId
     * @return
     */
    List<ConsumeLoanRateDayConfig> getByLoanConfigId(@Param("loanConfigId") Integer loanConfigId);
}
