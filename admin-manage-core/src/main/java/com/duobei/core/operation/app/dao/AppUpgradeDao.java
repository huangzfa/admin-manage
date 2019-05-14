package com.duobei.core.operation.app.dao;

import com.duobei.core.operation.app.domain.AppUpgrade;
import com.duobei.core.operation.app.domain.criteria.AppUpgradeCriteria;
import com.duobei.core.operation.app.domain.vo.AppUpgradeVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
public interface AppUpgradeDao {
    List<AppUpgradeVo> getPage(AppUpgradeCriteria appUpgradeCriteria);

    int countByCriteria(AppUpgradeCriteria criteria);

    int delete(AppUpgrade appUpgrade);

    int save(AppUpgrade entity);

    int update(AppUpgrade entity);

    int updateState(AppUpgrade entity);
}
