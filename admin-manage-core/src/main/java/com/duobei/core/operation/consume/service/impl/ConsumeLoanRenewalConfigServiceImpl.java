package com.duobei.core.operation.consume.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.core.operation.consume.dao.ConsumeLoanRenewalConfigDao;
import com.duobei.core.operation.consume.domain.ConsumeLoanRenewalConfig;
import com.duobei.core.operation.consume.service.ConsumeLoanRenewalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/4
 */
@Service("consumeLoanRenewalConfigService")
public class ConsumeLoanRenewalConfigServiceImpl implements ConsumeLoanRenewalConfigService {

    @Autowired
    private ConsumeLoanRenewalConfigDao renewalConfigDao;
    //默认配置值
    private final int[][] defaultRenewalConfig= {{0,0},{1,3},{4,30}};

    /**
     * 根据消费贷配置id查询利率天数配置
     * @param configId
     * @return
     */
    @Override
    public List<ConsumeLoanRenewalConfig> getByConfigId(Integer configId){
        List<ConsumeLoanRenewalConfig> list = renewalConfigDao.getByConfigId(configId);
        //制造假数据
        if( list.size() == 0){
            for(int  i = 0 ;i < defaultRenewalConfig.length ;i++){
                ConsumeLoanRenewalConfig config = new ConsumeLoanRenewalConfig();
                config.setId(BizConstant.INT_ZERO);
                config.setConsumeLoanConfigId(BizConstant.INT_ZERO);
                config.setStartDay(defaultRenewalConfig[i][BizConstant.INT_ZERO]);
                config.setEndDay(defaultRenewalConfig[i][BizConstant.INT_ONE]);
                list.add(config);
            }
        }
        return list;
    }

}
