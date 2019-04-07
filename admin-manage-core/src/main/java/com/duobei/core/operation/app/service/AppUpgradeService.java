package com.duobei.core.operation.app.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.app.domain.AppUpgrade;
import com.duobei.core.operation.app.domain.criteria.AppUpgradeCriteria;
import com.duobei.core.operation.app.domain.vo.AppUpgradeVo;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
public interface AppUpgradeService {
    ListVo<AppUpgradeVo> getListVoByQuery(AppUpgradeCriteria appUpgradeCriteria);

    AppUpgrade getById(Integer id);

    void delete(AppUpgrade appUpgrade) throws TqException;

    void save(AppUpgrade entity) throws TqException;

    void update(AppUpgrade entity) throws TqException;

    void updateStatus(AppUpgrade entity) throws TqException;
}
