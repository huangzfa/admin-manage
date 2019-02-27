package com.duobei.core.operation.product.dao;

import com.duobei.common.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.criteria.MerchantCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@DataSourceSwitch(dataSource = DataSourceConst.OPERATE)
public interface MerchantDao {

    /**
     *
     * @param criteria
     * @return
     */
    List<Merchant> getPageList(MerchantCriteria criteria);

    /**
     *
     * @param criteria
     * @return
     */
    int countByCriteria(MerchantCriteria criteria);
    /**
     * 查询所有有效记录
     * @return
     */
    List<Merchant> getAll();

    /**
     * 修改商户
     * @param merchant
     */
    void update(Merchant merchant);

    /**
     * 添加商户
     * @param merchant
     */
    void save(Merchant merchant);
}
