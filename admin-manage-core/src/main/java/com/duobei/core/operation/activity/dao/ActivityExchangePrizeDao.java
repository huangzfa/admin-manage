package com.duobei.core.operation.activity.dao;

import com.duobei.core.operation.activity.domain.ActivityExchangePrize;
import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/18
 */
public interface ActivityExchangePrizeDao {
    List<ActivityExchangePrizeVo> getByActId(HashMap<String,Object> param);

    int insert(ActivityExchangePrize prize);

    int update(ActivityExchangePrize prize);

    int batchDelete(@Param("actId") Integer actId);

}
