package com.duobei.core.operation.consume.service.impl;

import com.duobei.core.operation.consume.dao.ConsumeLoanRateDayConfigDao;
import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfig;
import com.duobei.core.operation.consume.service.ConsumeLoanRateDayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
@Service("ConsumeLoanRateDayConfigService")
public class ConsumeLoanRateDayConfigServiceImpl implements ConsumeLoanRateDayConfigService {

    @Autowired
    private ConsumeLoanRateDayConfigDao consumeLoanRateDayConfigDao;
    /**
     * 根据消费贷配置id，查询利率区间配置
     * @param loanConfigId
     * @return
     */
    @Override
    public List<ConsumeLoanRateDayConfig> getByLoanConfigId(Integer loanConfigId){
        return consumeLoanRateDayConfigDao.getByLoanConfigId(loanConfigId);
    }
}
