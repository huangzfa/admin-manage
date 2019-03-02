package com.duobei.core.operation.product.dao;


import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.criteria.MerchantCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
public interface MerchantDao {

    /**
     *分页查询
     * @param criteria
     * @return
     */
    List<Merchant> getPageList(MerchantCriteria criteria);

    /**
     *分页查询求total数量
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
    int update(Merchant merchant);

    /**
     * 添加商户
     * @param merchant
     */
    int save(Merchant merchant);

    /**
     *
     * @param id
     * @return
     */
    Merchant getById(Integer id);

    /**
     *
     * @param merchantNo
     * @return
     */
    Merchant getByMerchantNo(@Param("merchantNo") String merchantNo);

    /**
     * 查询数据库中最后一个商户
     * @return
     */
    Merchant getLastOne();
}
