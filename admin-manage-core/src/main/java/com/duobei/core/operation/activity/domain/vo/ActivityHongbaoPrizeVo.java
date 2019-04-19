package com.duobei.core.operation.activity.domain.vo;

import com.duobei.core.operation.activity.domain.ActivityHongbaoPrize;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/18
 */
@Data
public class ActivityHongbaoPrizeVo extends ActivityHongbaoPrize {
    private  String prizeType;
    private String prizeName;
}
