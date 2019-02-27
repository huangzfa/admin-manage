package com.duobei.core.manage.auth.service.impl;


import com.duobei.core.manage.auth.dao.RoleDataAuthDao;
import com.duobei.core.manage.auth.domain.RoleDataAuth;
import com.duobei.core.manage.auth.domain.vo.RoleDataAuthVo;
import com.duobei.core.manage.auth.service.RoleDataAuthService;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@Service("RoleDataAuthService")
public class RoleDataAuthServiceImpl implements RoleDataAuthService {

    @Autowired
    private RoleDataAuthDao roleDataAuthDao;
    @Autowired
    private ProductDao productDao;


    /**
     * 根据角色查角色数据权限,并设置哪些产品勾选过
     * @param roleId
     * @return
     */
    @Override
    public List<RoleDataAuthVo> getByRoleId(Integer roleId){
        List<RoleDataAuth> list = roleDataAuthDao.getByRoleId(roleId);
        List<Product> productList = productDao.getAll();
        List<RoleDataAuthVo> voList = new ArrayList<>();
        for( Product product : productList){
            RoleDataAuthVo vo = new RoleDataAuthVo();
            vo.setProductId(product.getId());
            vo.setProductName(product.getProductName());
            for( RoleDataAuth auth : list){
                if(product.getId().equals(auth.getProductId())){
                    vo.setChecked("checked");
                    vo.setId(auth.getId());
                    vo.setRoleId(auth.getRoleId());
                }
            }
            voList.add(vo);
        }
        return voList;
    }
}
