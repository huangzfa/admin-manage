package com.duobei.core.operation.consume.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.core.operation.consume.dao.ConsumeLoanRateDayConfigDao;
import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfig;
import com.duobei.core.operation.consume.service.ConsumeLoanRateDayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        List<ConsumeLoanRateDayConfig> list = consumeLoanRateDayConfigDao.getByLoanConfigId(loanConfigId);
        if( list.size() == 0){
            //设置默认数据
            BigDecimal maxRate= new BigDecimal(0.017).setScale(3, RoundingMode.HALF_UP);//最大日利率
            BigDecimal minxRate= new BigDecimal(0.003).setScale(3, RoundingMode.HALF_UP);//最小日利率
            while (maxRate.compareTo(minxRate) >BizConstant.INT_ZERO){
                ConsumeLoanRateDayConfig config = new ConsumeLoanRateDayConfig();
                config.setId(BizConstant.INT_ZERO);
                config.setDayRate(minxRate);
                config.setBorrowDays("7");
                list.add(config);
                minxRate= minxRate.add(new BigDecimal(0.001));
                minxRate=minxRate.setScale(3, RoundingMode.HALF_UP);
            }
        }
        return list;
    }

    @Override
    public void batchSave(List<ConsumeLoanRateDayConfig> list){
        consumeLoanRateDayConfigDao.batchSave(list);
    }

    @Override
    public void batchUpdate(List<ConsumeLoanRateDayConfig> list){
        consumeLoanRateDayConfigDao.batchUpdate(list);
    }
}
