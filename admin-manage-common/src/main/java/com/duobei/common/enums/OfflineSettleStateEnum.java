package com.duobei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huangzhongfa
 * @description  平账状态
 * @date 2019/5/20
 */
@AllArgsConstructor
public enum OfflineSettleStateEnum {

    OFFLINE_SETTLE_STATE_WAIT(0,"待平账"),
    OFFLINE_SETTLE_STATE_SUCCESS(1,"平账成功"),
    OFFLINE_SETTLE_STATE_FAIL(1,"平账失败");
    ;

    @Getter
    private int type;

    @Getter
    private String desc;
}
