package com.duobei.core.auth.domain.vo;

import com.duobei.core.auth.domain.OperatorRoleKey;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/1/3
 */
public class OperatorRoleVo extends OperatorRoleKey{
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
