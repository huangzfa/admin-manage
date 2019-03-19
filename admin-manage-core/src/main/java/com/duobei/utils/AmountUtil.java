package com.duobei.utils;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/19
 */
public class AmountUtil {

    public static Long getLongAmount(BigDecimal amount){
        if (amount == null) {
            return 0L;
        }
            return amount.multiply(new BigDecimal(100)).longValue();

        }

    public static BigDecimal getBigDecimal (Long amount){
        if (amount == null){
            return BigDecimal.ZERO;
        }
        return new BigDecimal(amount).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
    }
}
