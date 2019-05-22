package com.duobei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**短信模板枚举类
 * @author huangzhongfa
 * @description
 * @date 2019/5/17
 */
@AllArgsConstructor
public enum  SmsTempletEnum {
    ORDER_OVERDUE_ONE_DAY("ORDER_OVERDUE_ONE_DAY", "逾期一天",1),
    ORDER_OVERDUE_TWO_DAY("ORDER_OVERDUE_TWO_DAY", "逾期两天",2),
    ORDER_OVERDUE_THREE_DAY("ORDER_OVERDUE_THREE_DAY", "逾期三天",3),
    ORDER_OVERDUE_ADVAN_ONE_DAY("ORDER_OVERDUE_ADVAN_ONE_DAY", "即将逾期一天",-1),
    ORDER_OVERDUE_ADVAN_TWO_DAY("ORDER_OVERDUE_ADVAN_TWO_DAY", "即将逾期两天",-2),
    ORDER_OVERDUE_ADVAN_THREE_DAY("ORDER_OVERDUE_ADVAN_THREE_DAY", "即将逾期三天",-3);

    @Getter
    private String code;

    @Getter
    private String des;

    @Getter
    private Integer day;

    public static String findCodeByDay(Integer day) {
        for (SmsTempletEnum environmentType : SmsTempletEnum.values()) {
            if (environmentType.getDay().equals(day)) {
                return environmentType.getCode();
            }
        }
        return null;
    }
}
