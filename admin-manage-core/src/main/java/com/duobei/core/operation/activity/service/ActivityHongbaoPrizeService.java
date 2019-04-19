package com.duobei.core.operation.activity.service;

import com.duobei.core.operation.activity.domain.vo.ActivityHongbaoPrizeVo;

import java.util.HashMap;
import java.util.List;

/**红包签到奖品
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
public interface ActivityHongbaoPrizeService {
    /**
     * 查询红包活动签到奖品
     * @param params
     * @return
     */
    List<ActivityHongbaoPrizeVo> getByActId(HashMap<String,Object> params);
}
