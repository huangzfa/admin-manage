package com.duobei.core.operation.push.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
@Data
public class PushRecordCriteria extends Pagination {
    /**
     * 推送开始时间
     */
    private String pushStartTime;

    /**
     * 推送结束时间
     */
    private String pushEndTime;

    private Integer productId;

    /**
     *
     */
    private Integer addOperatorId;

    /**
     *
     */
    private String addOperatorName;
}
