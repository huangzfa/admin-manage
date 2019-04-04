package com.duobei.core.operation.functionSwitch.service;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.functionSwitch.domain.FunctionSwitchApp;
import com.duobei.core.operation.functionSwitch.domain.criteria.FunctionSwitchCriteria;
import com.duobei.core.operation.functionSwitch.domain.vo.FunctionSwitchAppVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/2
 */
public interface FunctionSwitchService {
    List<FunctionSwitchAppVo> getListByQuery(FunctionSwitchCriteria functionSwitchCriteria);

    void updateStatus(FunctionSwitchApp entity) throws TqException;

    void addSwitchApp(FunctionSwitchApp entity) throws TqException;
}
