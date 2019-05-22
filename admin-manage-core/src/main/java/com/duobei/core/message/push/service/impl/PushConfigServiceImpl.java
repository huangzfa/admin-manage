package com.duobei.core.message.push.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.message.push.dao.PushConfigDao;
import com.duobei.core.message.push.domain.PushConfig;
import com.duobei.core.message.push.domain.criteria.PushConfigCriteria;
import com.duobei.core.message.push.service.PushConfigService;
import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
@Service("pushConfigService")
public class PushConfigServiceImpl implements PushConfigService {

    @Autowired
    private PushConfigDao pushConfigDao;
    @Autowired
    private AppDao appDao;

    @Override
    public ListVo<PushConfig> getPage(PushConfigCriteria criteria){
        int total = pushConfigDao.countByCriteria(criteria);
        List<PushConfig> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = pushConfigDao.getPageList(criteria);
        }
        return new ListVo<PushConfig>(total, list);
    }

    @Override
    public void save(PushConfig config) throws TqException{
        App app = new App();
        app.setAppKey(config.getAppKey());
        if( appDao.countByAppKey(app) <BizConstant.INT_ONE ){
           throw new TqException("应用不存在");
        }
        if( pushConfigDao.countByAppKey(config) >BizConstant.INT_ZERO){
            throw new TqException("appKey已存在");
        }
        if( pushConfigDao.save(config) <BizConstant.INT_ONE){
            throw  new TqException("保存失败");
        }
    }

    @Override
    public void update(PushConfig config) throws TqException{
        App app = new App();
        app.setAppKey(config.getAppKey());
        if( appDao.countByAppKey(app) <BizConstant.INT_ONE ){
            throw new TqException("应用不存在");
        }
        if( pushConfigDao.countByAppKey(config) >BizConstant.INT_ZERO){
            throw new TqException("appKey已存在");
        }
        if( pushConfigDao.update(config) <BizConstant.INT_ONE){
            throw  new TqException("保存失败");
        }
    }

    @Override
    public void editState(PushConfig config) throws TqException{
        if( pushConfigDao.update(config) <BizConstant.INT_ONE){
            throw  new TqException("修改是啊比");
        }
    }
    @Override
    public PushConfig getById(Long id){
        return pushConfigDao.getById(id);
    }

    @Override
    public int countByAppKey(PushConfig config){
        return pushConfigDao.countByAppKey(config);
    }
}
