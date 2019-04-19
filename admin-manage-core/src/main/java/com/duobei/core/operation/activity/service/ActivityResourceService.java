package com.duobei.core.operation.activity.service;

import com.duobei.core.operation.activity.domain.ActivityResource;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityResourceService  {
    List<ActivityResource> getListByEnivr(HashMap<String,Object> params);
}
