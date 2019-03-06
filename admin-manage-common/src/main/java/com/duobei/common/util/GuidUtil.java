package com.duobei.common.util;

import com.duobei.common.enums.IdWorker;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
public class GuidUtil {
    /**
     * 商品标识符
     */
    public static final String GOODS_NO = "goods";

    /**
     * 生产商品编号
     *
     * @return
     */
    public static String generateRiskQuotaApplyOrderNo() {

        return GOODS_NO + IdWorker.ID_WORKER.nextId();
    }
}
