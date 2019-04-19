package com.duobei.core.operation.activity.domain.vo;

import com.duobei.core.operation.activity.domain.ActivityExchangePrize;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/18
 */
@Data
public class ActivityExchangePrizeVo extends ActivityExchangePrize{
    private  String prizeType;
    private String prizeName;
}
