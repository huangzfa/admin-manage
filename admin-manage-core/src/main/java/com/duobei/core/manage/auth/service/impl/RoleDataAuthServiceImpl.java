package com.duobei.core.manage.auth.service.impl;


import com.duobei.common.exception.TqException;
import com.duobei.core.manage.auth.dao.OperatorRoleDao;
import com.duobei.core.manage.auth.dao.RoleDataAuthDao;
import com.duobei.core.manage.auth.domain.RoleDataAuth;
import com.duobei.core.manage.auth.domain.vo.OperatorRoleVo;
import com.duobei.core.manage.auth.domain.vo.RoleDataAuthVo;
import com.duobei.core.manage.auth.domain.vo.RoleVo;
import com.duobei.core.manage.auth.service.RoleDataAuthService;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private OperatorRoleDao operatorRoleDao;


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

    @Override
    public void save(Integer roleId,String productIds) {
        String[] arrId = productIds.split(",");
        roleDataAuthDao.deleteByRoleId(roleId);
        List<RoleDataAuth> list = new ArrayList<>();
        for( String productId :arrId){
            RoleDataAuth auth = new RoleDataAuth();
            auth.setProductId(Integer.parseInt(productId));
            auth.setRoleId(roleId);
            list.add(auth);
        }
        roleDataAuthDao.saveBatch(list);

    }

    /**
     * 返回登录用户所拥有的产品权限
     * @param opId
     * @return
     */
    @Override
    public List<Product> getByOpId(Integer opId){
        List<Integer> opIds = new ArrayList<>();
        opIds.add(opId);
        //获取用户所有角色权限
        List<OperatorRoleVo> roleList= operatorRoleDao.getRoleByOpIds(opIds);
        List<Integer> roleIds = roleList.stream().map(OperatorRoleVo::getRoleId).collect(Collectors.toList());
        //获取该用户角色数据关联表
        List<RoleDataAuth> roleDataAuths = roleDataAuthDao.getByListRoleId(roleIds);
        List<Integer> productIds = roleDataAuths.stream().map(RoleDataAuth::getProductId).collect(Collectors.toList());
        //获取该用户的产品
        return productDao.getByProductIds(productIds);
    }
}
