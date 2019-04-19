package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.ActivityResource;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityResourceDao {
    List<ActivityResource> getListByEnivr(HashMap<String,Object> params);
}
