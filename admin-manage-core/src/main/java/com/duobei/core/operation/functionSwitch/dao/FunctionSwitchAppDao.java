package com.duobei.core.operation.functionSwitch.dao;

import com.duobei.core.operation.functionSwitch.domain.FunctionSwitchApp;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/3
 */
public interface FunctionSwitchAppDao {
    @MapKey("switchId")
    Map<Integer,FunctionSwitchApp> getMapByAppId(Integer appId);

    int updateStatue(FunctionSwitchApp entity);

    int save(FunctionSwitchApp entity);
}
