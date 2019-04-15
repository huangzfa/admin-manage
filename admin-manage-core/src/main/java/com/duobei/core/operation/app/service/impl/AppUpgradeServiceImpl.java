package com.duobei.core.operation.app.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.app.dao.AppUpgradeDao;
import com.duobei.core.operation.app.dao.mapper.AppUpgradeMapper;
import com.duobei.core.operation.app.domain.AppUpgrade;
import com.duobei.core.operation.app.domain.AppUpgradeExample;
import com.duobei.core.operation.app.domain.criteria.AppUpgradeCriteria;
import com.duobei.core.operation.app.domain.vo.AppUpgradeVo;
import com.duobei.core.operation.app.service.AppUpgradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
@Service(value = "appUpgradeService")
public class AppUpgradeServiceImpl implements AppUpgradeService {

    @Resource
    AppUpgradeDao appUpgradeDao;
    @Resource
    AppUpgradeMapper appUpgradeMapper;
    @Override
    public ListVo<AppUpgradeVo> getListVoByQuery(AppUpgradeCriteria appUpgradeCriteria) {
        AppUpgradeExample example = new AppUpgradeExample();
        AppUpgradeExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        if (appUpgradeCriteria.getAppId() != null){
            criteria.andAppIdEqualTo(appUpgradeCriteria.getAppId());
        }else{
            criteria.andAppIdIn(appUpgradeCriteria.getAppIds());
        }
        if (appUpgradeCriteria.getVersionNumber() != null){
            criteria.andVersionNumberEqualTo(appUpgradeCriteria.getVersionNumber());
        }

        Long total = appUpgradeMapper.countByExample(example);
        List<AppUpgradeVo> appUpgradeVos = null;
        if (total > 0) {
            appUpgradeVos = appUpgradeDao.getListVoByQuery(appUpgradeCriteria);
        }

        return new ListVo<AppUpgradeVo>(total.intValue(),appUpgradeVos);
    }

    @Override
    public AppUpgrade getById(Integer id) {
        return appUpgradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(AppUpgrade appUpgrade) throws TqException {
        int count = appUpgradeDao.delete(appUpgrade);
        if (count != 1){
            throw new TqException("删除app升级信息失败");
        }
    }

    @Override
    public void save(AppUpgrade entity) throws TqException {
        int count = appUpgradeDao.save(entity);
        if (count != 1){
            throw new TqException("新增app升级信息失败");
        }
    }

    @Override
    public void update(AppUpgrade entity) throws TqException {
        int count = appUpgradeDao.update(entity);
        if (count != 1){
            throw new TqException("新增app升级信息失败");
        }
    }

    @Override
    public void updateStatus(AppUpgrade entity) throws TqException {
        int count = appUpgradeDao.updateState(entity);
        if (count != 1){
            throw new TqException("修改app升级信息失败");
        }
    }
}