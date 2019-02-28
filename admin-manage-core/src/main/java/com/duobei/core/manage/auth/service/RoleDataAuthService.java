package com.duobei.core.manage.auth.service;

import com.duobei.common.exception.TqException;
import com.duobei.core.manage.auth.domain.vo.RoleDataAuthVo;
import com.duobei.core.manage.auth.domain.vo.RoleVo;
import com.duobei.core.operation.product.domain.Product;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
public interface RoleDataAuthService {

    /**
     * 根据角色查询产品
     * @param roleId
     * @return
     */
    List<RoleDataAuthVo> getByRoleId(Integer roleId);

    void save(Integer roleId,String productIds) throws TqException;

    List<Product> getByOpId(Integer opId);

}
