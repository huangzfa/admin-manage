package com.duobei.core.operation.functionSwitch.dao;

import com.duobei.core.operation.functionSwitch.domain.FunctionSwitch;
import com.duobei.core.operation.functionSwitch.domain.criteria.FunctionSwitchCriteria;
import com.duobei.core.operation.functionSwitch.domain.vo.FunctionSwitchAppVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/2
 */
public interface FunctionSwitchDao {
    List<FunctionSwitch> getListByQuery(FunctionSwitchCriteria functionSwitchCriteria);

    List<FunctionSwitchAppVo> getListVoByQuery(FunctionSwitchCriteria functionSwitchCriteria);
}
