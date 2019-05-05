package com.duobei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**活动奖品类型
 * Created by 15935 on 2018/8/13.
 */
@AllArgsConstructor
public enum PrizeTypeEnum {
    JKQ("jk", "借款券"),
    HKQ("hk", "还款券"),
    BZJ("bzj", "不中奖"),
    JYJP("jyjp", "自由奖品");
    @Getter
    private String env;
    @Getter
    private String desc;
}


