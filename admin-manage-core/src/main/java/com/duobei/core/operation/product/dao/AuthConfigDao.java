package com.duobei.core.operation.product.dao;

import com.duobei.core.operation.product.domain.AuthConfig;
import com.duobei.core.operation.product.domain.vo.AuthConfigVo;
import com.duobei.core.operation.product.domain.criteria.AuthConfigCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/1
 */
public interface AuthConfigDao {
    List<AuthConfigVo> getAll();

    List<AuthConfigVo> getListByType(@Param("authType") String authType, @Param("name") String name);

    List<AuthConfig> getPageList(AuthConfigCriteria criteria);

    int countByCriteria(AuthConfigCriteria userCriteria);

    AuthConfig getByCode(@Param("code") String code);

    int updateState(@Param("isEnable") String isEnable,@Param("code") String code);

    int update(AuthConfig record);
}
