package com.duobei.core.operation.product.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
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
    private ProductDao pgyProductDao;

    /**
     * 分页查询
     *
     * @param criteria
     * @return
     */
    @Override
    public ListVo<Product> getLists(ProductCriteria criteria) {
        int total = pgyProductDao.countByCriteria(criteria);
        List<Product> list = null;
        if (total > 0) {
            list = pgyProductDao.getPageList(criteria);
        }
        return new ListVo<Product>(total, list);
    }

    @Override
    public Product getById(Integer id) {
        return pgyProductDao.getById(id);
    }

    @Override
    public Product getByCode(String code) {
        return pgyProductDao.getByCode(code);
    }

    /**
     * 禁用启用产品
     * @param record
     * @return
     */
    @Override
    public int editState(Product record){
        return pgyProductDao.update(record);
    }
}
