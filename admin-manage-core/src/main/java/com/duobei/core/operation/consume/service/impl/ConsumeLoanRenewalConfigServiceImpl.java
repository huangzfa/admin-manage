package com.duobei.core.operation.consume.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.core.operation.consume.dao.ConsumeLoanRenewalConfigDao;
import com.duobei.core.operation.consume.domain.ConsumeLoanRenewalConfig;
import com.duobei.core.operation.consume.domain.vo.ConsumeLoanRenewalConfigVo;
import com.duobei.core.operation.consume.service.ConsumeLoanRenewalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private final String[] configName = {"未逾期需还最低本金比例","逾期1~3天需还最低本金比例","逾期>3天需还最低本金比例"};
    /**
     * 根据消费贷配置id查询利率天数配置
     * @param configId
     * @return
     */
    @Override
    public List<ConsumeLoanRenewalConfigVo> getByConfigId(Integer configId){
        List<ConsumeLoanRenewalConfigVo> list = renewalConfigDao.getByConfigId(configId);
        //制造假数据
        for(int  i = 0 ;i < defaultRenewalConfig.length ;i++){
            ConsumeLoanRenewalConfigVo config = new ConsumeLoanRenewalConfigVo();
            for( ConsumeLoanRenewalConfigVo vo : list){
                if(vo.getEndDay().equals(defaultRenewalConfig[i][BizConstant.INT_ONE])){
                    config.setId(vo.getId());
                    vo.setConfigName(configName[i]);
                    break;
                }
            }
            if( config.getId() == null ){
                config.setId(BizConstant.INT_ZERO);
                config.setConsumeLoanConfigId(BizConstant.INT_ZERO);
                config.setStartDay(defaultRenewalConfig[i][BizConstant.INT_ZERO]);
                config.setEndDay(defaultRenewalConfig[i][BizConstant.INT_ONE]);
                config.setRenewalCapitalRate(new BigDecimal(BizConstant.INT_ZERO));
                config.setConfigName(configName[i]);
                list.add(config);
            }
        }
        list.sort((a, b) -> a.getEndDay() - b.getEndDay());
        return list;
    }

    @Override
    public int save (ConsumeLoanRenewalConfig renewalConfig){
        return renewalConfigDao.save(renewalConfig);
    }

    @Override
    public int update (ConsumeLoanRenewalConfig renewalConfig){
        return renewalConfigDao.update(renewalConfig);
    }
}
