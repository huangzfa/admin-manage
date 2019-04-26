package com.duobei.core.operation.product.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.MerchantCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 商户管理
 * @date 2019/2/27
 */
public interface MerchantService {

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    ListVo<Merchant> getPageList(MerchantCriteria criteria);

    /**
     * 查询所有有效记录
     * @return
     */
    List<Merchant> getAll();

    /**
     * 修改商户
     * @param merchant
     */
    void update(Merchant merchant) throws TqException;

    /**
     * 添加商户
     * @param merchant
     */
    void save(Merchant merchant) throws TqException;

    /**
     * 根据商户编号查询
     * @param merchantNo
     * @return
     */
    Merchant getByMerchantNo(String merchantNo);

    /**
     *
     * @param id
     * @return
     */
    Merchant getById(Integer id);

    void noticeByProduct(Product product);

    void noticeByMerchant(Merchant merchant);

}
