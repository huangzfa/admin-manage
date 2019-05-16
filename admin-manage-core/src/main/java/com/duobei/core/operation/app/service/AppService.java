package com.duobei.core.operation.app.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.operation.app.domain.criteria.AppCriteria;
import com.duobei.core.operation.app.domain.vo.AppVo;
import com.duobei.core.operation.product.domain.Product;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface AppService {

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    ListVo<AppVo> getLists(AppCriteria criteria);
    /**
     * 根据产品查询app
     * @param list
     * @return
     */
    List<App> getByProductIds(List<Product> list);

    /**
     * 查询所有app列表
     * @return
     */
    List<App> getAll();

    /**
     * 根据id查询app
     * @param id
     */
    App getAppById(Integer id);

    /**
     *
     * @param app
     * @return
     */
    void save(App app,OperatorCredential credential) throws TqException;

    /**
     *
     * @param app
     * @return
     */
    void update(App app,OperatorCredential credential) throws TqException;
}
