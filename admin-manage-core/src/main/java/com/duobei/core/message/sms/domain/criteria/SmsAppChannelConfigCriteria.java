package com.duobei.core.message.sms.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
@Data
public class SmsAppChannelConfigCriteria extends Pagination {
    private List<String> appKeyList;
}
