package com.duobei.core.operation.push.domain.vo;

import com.duobei.core.operation.push.domain.PushRecord;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
@Data
public class PushRecordVo extends PushRecord{

    private String productName;

    private String appName;

    private String addOperatorName;
}
