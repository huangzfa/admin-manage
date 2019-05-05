package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.ActivityPrizeRel;
import com.duobei.core.operation.activity.domain.vo.ActivityPrizeRelVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityPrizeRelDao {

    List<ActivityPrizeRelVo> getByActId(HashMap<String,Object> params);

    List<ActivityPrizeRel> getByPrizeId(HashMap<String,Object> params);
}
