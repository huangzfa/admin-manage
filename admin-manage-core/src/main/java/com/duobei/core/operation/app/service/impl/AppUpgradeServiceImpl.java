package com.duobei.core.operation.app.service.impl;

import com.duobei.common.constant.BizConstant;
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
    public ListVo<AppUpgradeVo> getPage(AppUpgradeCriteria appUpgradeCriteria) {
        int total = appUpgradeDao.countByCriteria(appUpgradeCriteria);
        List<AppUpgradeVo> appUpgradeVos = null;
        if (total > BizConstant.INT_ZERO) {
            appUpgradeVos = appUpgradeDao.getPage(appUpgradeCriteria);
        }

        return new ListVo<AppUpgradeVo>(total,appUpgradeVos);
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
