package com.duobei.core.operation.product.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.domain.AuthConfig;
import com.duobei.core.operation.product.domain.vo.AuthConfigVo;
import com.duobei.core.operation.product.domain.criteria.AuthConfigCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 认证配置项service
 * @date 2019/3/1
 */
public interface AuthConfigService {
    /**
     * 查询所有有效认证项
     * @return
     */
    List<AuthConfig> getAll();

    /**
     * type:1基础认证，2补充认证
     * @param type
     * @param name 根据名称模糊查询
     * @return
     */
    List<AuthConfigVo> getListByType(String type, String name);

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    ListVo<AuthConfig> getLists(AuthConfigCriteria criteria);

    AuthConfig getByCode(String code);

    void save (AuthConfig entity) throws TqException;

    void update(AuthConfig entity) throws TqException;

    void editState(String code,String authState) throws RuntimeException;
}
