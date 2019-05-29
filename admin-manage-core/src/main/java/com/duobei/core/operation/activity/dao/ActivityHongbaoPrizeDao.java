package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.ActivityHongbaoPrize;
import com.duobei.core.operation.activity.domain.vo.ActivityHongbaoPrizeVo;
import org.apache.ibatis.annotations.Param;

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

    /**
     *删除奖品时候查询
     * @param params
     * @return
     */
    List<ActivityHongbaoPrize> getByPrizeId(HashMap<String,Object> params);

    int insert(ActivityHongbaoPrize record);

    int update(ActivityHongbaoPrize record);

    int batchDelete(@Param("actId") Integer actId);

}
