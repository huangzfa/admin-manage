package com.duobei.core.transaction.borrow.domain.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/21
 */
@Data
@Accessors(chain = true)
public class OverdueDerateBo {

    /**产品ID*/
    private Integer productId;

    /** 商户ID*/
    private Integer merchantId;

    private String borrowNo;

    private Long derateAmount;


    private String sign;

    private String timestamp;

}
