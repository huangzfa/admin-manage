package com.duobei.core.operation.product.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import com.duobei.core.operation.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 分页查询
     *
     * @param criteria
     * @return
     */
    @Override
    public ListVo<ProductVo> getLists(ProductCriteria criteria) {
        int total = productDao.countByCriteria(criteria);
        List<ProductVo> list = null;
        if (total > 0) {
            list = productDao.getPageList(criteria);
        }
        return new ListVo<ProductVo>(total, list);
    }

    @Override
    public Product getById(Integer id) {
        return productDao.getById(id);
    }

    @Override
    public Product getByCode(String code) {
        return productDao.getByCode(code);
    }

    /**
     * 禁用启用产品
     * @param record
     * @return
     */
    @Override
    public int editState(Product record){
        return productDao.update(record);
    }
}
