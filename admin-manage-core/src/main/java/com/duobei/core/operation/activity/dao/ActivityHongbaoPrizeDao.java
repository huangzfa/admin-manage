package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.vo.ActivityHongbaoPrizeVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityHongbaoPrizeDao {
    /**
     * 查询红包活动签到奖品
     * @param params
     * @return
     */
    List<ActivityHongbaoPrizeVo> getByActId(HashMap<String,Object> params);
}
