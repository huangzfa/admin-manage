package com.duobei.core.message.push.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/15
 */
@Data
public class PushConfigCriteria extends Pagination {
    private List<String> appKeyList;
}
