package com.duobei.core.operation.app.domain.vo;

import com.duobei.core.operation.app.domain.App;
import lombok.Data;


/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/4
 */
@Data
public class AppVo extends App {
    private String productName;
    private String merchantName;
}
