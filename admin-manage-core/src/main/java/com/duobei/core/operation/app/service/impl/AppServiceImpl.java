package com.duobei.core.operation.app.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.dao.mapper.AppMapper;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.operation.app.domain.criteria.AppCriteria;
import com.duobei.core.operation.app.domain.vo.AppVo;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Slf4j
@Service("appService")
public class AppServiceImpl implements AppService{

    @Autowired
    private AppDao appDao;

    @Resource
    private AppMapper appMapper;

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<AppVo> getLists(AppCriteria criteria){
        int total = appDao.countByCriteria(criteria);
        List<AppVo> list = null;
        if (total > 0) {
            list = appDao.getPageList(criteria);
        }
        return new ListVo<AppVo>(total, list);
    }

    /**
     * 根据产品查询app
     * @param list
     * @return
     */
    @Override
    public List<App> getByProductIds(List<Product> list){
        if( list == null || list.size() == 0){
            return null;
        }
        List<Integer> productIds = list.stream().map(Product::getId).collect(Collectors.toList());
        return appDao.getByProductIds(productIds);
    }

    /**
     * 查询所有app列表
     * @return
     */
    @Override
    public List<App> getAll(){
        return appDao.getAll();
    }

    @Override
    public App getAppById(Integer id) {
        return appMapper.selectByPrimaryKey(id);
    }

    /**
     *
     * @param app
     * @return
     */
    @Override
    public void save(App app,OperatorCredential credential) throws TqException{
        if( appDao.countByAppKey(app) >BizConstant.INT_ZERO){
            throw new TqException("appKey已存在");
        }
        if(appDao.save(app) <1){
            throw new TqException("添加失败");
        }
        credential.setAppList(getByProductIds(credential.getProductList()));
    }

    /**
     *
     * @param app
     * @return
     */
    @Override
    public void update(App app,OperatorCredential credential) throws TqException{
        if( appDao.countByAppKey(app) > BizConstant.INT_ZERO){
            throw new TqException("appKey已存在");
        }
        if(appDao.update(app) <1){
            throw new TqException("修改失败");
        }
        credential.setAppList(getByProductIds(credential.getProductList()));
    }
}
