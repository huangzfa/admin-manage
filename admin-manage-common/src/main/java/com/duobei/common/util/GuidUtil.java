package com.duobei.common.util;

import com.duobei.common.enums.IdWorker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    protected final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 生产商品编号
     *
     * @return
     */
    public static String getGoosNo() {

        return GOODS_NO + IdWorker.ID_WORKER.nextId();
    }

    /**
     * 产生唯一 的序列号。
     *
     * @return
     */
    public static String getSerialNumber() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return sdf.format(new Date()).substring(2, 8) + String.format("%010d", hashCode);
    }
}
