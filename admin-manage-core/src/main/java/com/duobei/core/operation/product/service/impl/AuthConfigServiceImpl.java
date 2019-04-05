package com.duobei.core.operation.product.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.AuthConfigDao;
import com.duobei.core.operation.product.dao.mapper.AuthConfigMapper;
import com.duobei.core.operation.product.domain.AuthConfig;
import com.duobei.core.operation.product.domain.vo.AuthConfigVo;
import com.duobei.core.operation.product.service.AuthConfigService;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.AuthConfigCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 认证配置项service
 * @date 2019/3/1
 */
@Service("AuthConfigService")
@Slf4j
public class AuthConfigServiceImpl implements AuthConfigService {

    @Autowired
    private AuthConfigDao authConfigDao;
    @Autowired
    private AuthConfigMapper authConfigMapper;
    @Autowired
    private ProductDao productDao;

    /**
     * type:1基础认证，2补充认证
     * @param type
     * @param name 根据名称模糊查询
     * @return
     */
    @Override
    public List<AuthConfigVo> getListByType(String type, String name){
        return authConfigDao.getListByType(type,name);
    }

    /**
     * 查询所有认证项
     * @return
     */
    @Override
    public List<AuthConfigVo> getAll(){
        return authConfigDao.getAll();
    }

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<AuthConfig> getLists(AuthConfigCriteria criteria){
        int total = authConfigDao.countByCriteria(criteria);
        List<AuthConfig> list = null;
        if (total > 0) {
            list = authConfigDao.getPageList(criteria);
        }
        return new ListVo<AuthConfig>(total, list);
    }

    /**
     * 通过code查询
     * @param code
     * @return
     */
    @Override
    public AuthConfig getByCode(String code){
        return authConfigDao.getByCode(code);
    }

    @Override
    public void save (AuthConfig entity) throws TqException{
        if( authConfigMapper.insertSelective(entity) !=1){
            throw new TqException("认证项配置保存失败");
        }
    }

    @Override
    public void update(AuthConfig entity) throws TqException{
        if( authConfigDao.update(entity) !=1 ){
            throw new TqException("认证项配置修改失败");
        }
    }

    /**
     * 修改启用禁用状态
     * @param code
     * @param authState
     * @throws RuntimeException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void editState(String code,String authState) throws RuntimeException{
        AuthConfig entity = authConfigDao.getByCode(code);
        if( entity == null ){
            throw new RuntimeException("认证项不存在");
        }
        //如果是禁用
        if( authState.equals("0") ){
            List<Product> list = productDao.getByAuthId(entity.getId());
            if( list.size() >0 ){
                String msg = "操作失败,该认证项在"+list.get(0).getProductName();
                if( list.size() > 1){
                    msg+="、"+list.get(1).getProductName();
                }
                if( list.size() > 2){
                    msg+="、"+list.get(2).getProductName();
                }
                msg += "等"+list.size()+"个产品中配置了,请解绑后尝试";
                throw new RuntimeException(msg);
            }
        }
        if( authConfigDao.updateState(authState,code) !=1){
            throw new RuntimeException("认证项配置修改状态失败");
        }

    }

}
