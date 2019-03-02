package com.duobei.core.operation.product.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.encrypt.MD5Util;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import com.duobei.core.operation.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    /**
     * 根据产品code查询
     * @param code
     * @return
     */
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
    public void editState(Product record) throws TqException{
        if( productDao.update(record) < 1){
            throw new TqException("禁用失败");
        }
    }

    /**
     * 修改商户
     * @param product
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void update(Product product) throws TqException{
        if( productDao.update(product) <1){
            throw new TqException("修改失败");
        }
    }

    /**
     * 添加商户
     * @param product
     */
    @Override
    public void save(Product product) throws TqException{
        product.setProductCode(MD5Util.encrypt(DateUtil.getTimeStr(new Date())));
        if( productDao.save(product) <1){
            throw new TqException("添加失败");
        }
    }

    /**
     * 查询所有产品
     * @return
     */
    @Override
    public List<Product> getAll(){
        return productDao.getAll();
    }
}
