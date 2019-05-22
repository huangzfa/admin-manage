package com.duobei.core.message.sms.domain.criteria;

import com.duobei.common.util.Pagination;
import com.duobei.core.operation.app.domain.App;
import lombok.Data;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/15
 */
@Data
public class SmsTempletCriteria extends Pagination {
    private Integer productId;

    private List<App> appList;
}
