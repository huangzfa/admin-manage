package com.duobei.core.operation.product.domain.vo;


import com.duobei.core.operation.product.domain.AuthConfig;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description 认证配置项
 * @date 2019/3/1
 */
@Data
public class AuthConfigVo extends AuthConfig {
    private String checked;//是否选中

}
