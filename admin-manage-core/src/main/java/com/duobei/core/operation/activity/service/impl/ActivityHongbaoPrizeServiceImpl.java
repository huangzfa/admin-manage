package com.duobei.core.operation.activity.service.impl;

import com.duobei.core.operation.activity.dao.ActivityHongbaoPrizeDao;
import com.duobei.core.operation.activity.domain.vo.ActivityHongbaoPrizeVo;
import com.duobei.core.operation.activity.service.ActivityHongbaoPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description 红包活动其他奖品
 * @date 2019/4/11
 */
@Service("activityHongbaoPrizeService")
public class ActivityHongbaoPrizeServiceImpl implements ActivityHongbaoPrizeService {
    @Autowired
    private ActivityHongbaoPrizeDao hongbaoPrizeDao;

    /**
     * 查询红包活动签到奖品
     * @param params
     * @return
     */
    @Override
    public List<ActivityHongbaoPrizeVo> getByActId(HashMap<String,Object> params){
        return hongbaoPrizeDao.getByActId(params);
    }
}
