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
    private Integer authSort = 1;//排序，默认1
    private Integer productAuthId;//产品id
    private Byte isRequired = 0;//是否必填，默认不必填
    private String checked;//是否选中
    private String authGroup;//组编号

}
