package com.duobei.core.operation.app.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.app.dao.AppPageConfigDao;
import com.duobei.core.operation.app.dao.mapper.AppPageConfigMapper;
import com.duobei.core.operation.app.domain .AppPageConfig;
import com.duobei.core.operation.app.domain.AppPageConfigExample;
import com.duobei.core.operation.app.domain.AppPageConfigExample.Criteria;
import com.duobei.core.operation.app.domain.criteria.AppPageConfigCriteria;
import com.duobei.core.operation.app.service.AppPageConfigService;
import com.duobei.dic.ZD;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/2/27
 */
@Service("appPageConfigService")
public class AppPageConfigServiceImpl implements AppPageConfigService {

    private final static String DESC = "应用页面";

    @Resource
    AppPageConfigDao appPageConfigDao;

    @Resource
    AppPageConfigMapper appPageConfigMapper;

    @Override
    public ListVo<AppPageConfig> queryAppPageConfigList(AppPageConfigCriteria appPageConfigCriteria) {


        AppPageConfigExample example = new AppPageConfigExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andAppIdEqualTo(appPageConfigCriteria.getAppId());

        Long total = appPageConfigMapper.countByExample(example);
        List<AppPageConfig> appPages = null;
/*        if (total > 0) {*/
            appPages = appPageConfigDao.queryAppPageList(appPageConfigCriteria);
//        }
        return new ListVo<AppPageConfig>(total.intValue() , appPages);
    }

    @Override
    public AppPageConfig queryAppPageConfigById(Integer id) {
        return null;
    }

    @Override
    public void deleteAppPageConfig(AppPageConfig appPageConfig) throws TqException {
        int count = appPageConfigDao.deleteAppPageConfig(appPageConfig);
        if(count != 1){
            throw new TqException(DESC+"删除失败");
        }
    }

    @Override
    public void addAppPageConfig(AppPageConfig appPageConfig) throws TqException {
        int count = appPageConfigDao.addAppPageConfig(appPageConfig);
        if(count != 1){
            throw new TqException(DESC+"新增失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAppPageConfig( AppPageConfig appPageConfig) throws TqException {
        int count = appPageConfigDao.updateAppPageConfig(appPageConfig);
        if(count != 1){
            throw new TqException(DESC+"修改失败");
        }
    }
}
