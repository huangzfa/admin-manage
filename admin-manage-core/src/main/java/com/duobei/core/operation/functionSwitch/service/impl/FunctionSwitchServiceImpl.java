package com.duobei.core.operation.functionSwitch.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.functionSwitch.dao.FunctionSwitchAppDao;
import com.duobei.core.operation.functionSwitch.dao.FunctionSwitchDao;
import com.duobei.core.operation.functionSwitch.dao.mapper.FunctionSwitchMapper;
import com.duobei.core.operation.functionSwitch.domain.FunctionSwitchApp;
import com.duobei.core.operation.functionSwitch.domain.criteria.FunctionSwitchCriteria;
import com.duobei.core.operation.functionSwitch.domain.vo.FunctionSwitchAppVo;
import com.duobei.core.operation.functionSwitch.service.FunctionSwitchService;
import com.duobei.dic.ZD;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/2
 */
@Service("functionSwitchService")
public class FunctionSwitchServiceImpl implements FunctionSwitchService {
    @Resource
    FunctionSwitchMapper functionSwitchMapper;
    @Resource
    FunctionSwitchDao functionSwitchDao;

    @Resource
    FunctionSwitchAppDao functionSwitchAppDao;

    @Override
    public List<FunctionSwitchAppVo> getListByQuery(FunctionSwitchCriteria functionSwitchCriteria) {
        //查询开关
        List<FunctionSwitchAppVo> data =  functionSwitchDao.getListVoByQuery(functionSwitchCriteria);

        //查询应用的开关状态状态
        Map<Integer,FunctionSwitchApp> switchAppMap = functionSwitchAppDao.getMapByAppId(functionSwitchCriteria.getAppId());

        //数据处理
        for (FunctionSwitchAppVo functionSwitchAppVo : data){
            FunctionSwitchApp functionSwitchApp = switchAppMap.get(functionSwitchAppVo.getId());
            if (functionSwitchApp != null){
                functionSwitchAppVo.setSwitchAppId(functionSwitchApp.getId());
                functionSwitchAppVo.setIsEnable(functionSwitchApp.getIsEnable());
            }else{
                //默认禁用
                functionSwitchAppVo.setIsEnable(ZD.isEnable_no);
            }
        }
        return data;
    }



    @Override
    public void updateStatus(FunctionSwitchApp entity) throws TqException {
        int count = functionSwitchAppDao.updateStatue(entity);
        if (count != 1){
            throw new TqException("业务开关修改失败");
        }
    }

    @Override
    public void addSwitchApp(FunctionSwitchApp entity) throws TqException {
        int count = functionSwitchAppDao.save(entity);
        if (count != 1){
            throw new TqException("业务开关保存失败");
        }
    }
}
