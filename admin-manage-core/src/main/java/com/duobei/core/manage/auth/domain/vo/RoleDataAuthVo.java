package com.duobei.core.manage.auth.domain.vo;

import com.duobei.core.manage.auth.domain.RoleDataAuth;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@Data
public class RoleDataAuthVo extends RoleDataAuth{
    private String checked;
    private String productName;
}
