package com.duobei.core.operation.product.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.encrypt.MD5Util;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductBusinessDao;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.ProductBusiness;
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
    @Autowired
    private ProductBusinessDao productBusinessDao;

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
     * @param productVo
     * @return
     */
    @Override
    public void editState(ProductVo  productVo) throws TqException{
        if( productDao.update(productVo) < 1){
            throw new TqException("禁用失败");
        }
    }

    /**
     * 修改商户
     * @param productVo
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void updateMp(ProductVo  productVo) throws TqException{
        if( productDao.update(productVo) <1){
            throw new TqException("修改失败");
        }
        //保存业务类型,将所有state=1的，改为0
        productBusinessDao.updateState(productVo.getId(), BizConstant.INT_ZERO);
        String[] bizCodes = productVo.getBizCodes().split(",");
        for(String code :bizCodes){
            ProductBusiness entity = new ProductBusiness();
            entity.setProductId(productVo.getId());
            entity.setBizCode(code);
            ProductBusiness entity1 = productBusinessDao.getByBizCode(entity);
            //数据库中不存在
            if( entity1 == null){
                //新增操作
                entity.setAddOperatorId(productVo.getModifyOperatorId());
                productBusinessDao.save(entity);
            }else{
                //修改状态为有效
                entity.setId(entity1.getId());
                entity.setState(BizConstant.INT_ONE);
                productBusinessDao.update(entity);
            }
        }
    }

    /**
     * 添加商户
     * @param productVo
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void saveMp(ProductVo  productVo) throws TqException{
        productVo.setProductCode(MD5Util.encrypt(DateUtil.getTimeStr(new Date())));
        if( productDao.save(productVo) <1){
            throw new TqException("添加失败");
        }
        //保存业务类型
        String[] bizCodes = productVo.getBizCodes().split(",");
        for(String code :bizCodes){
            ProductBusiness entity = new ProductBusiness();
            entity.setProductId(productVo.getId());
            entity.setBizCode(code);
            ProductBusiness entity1 = productBusinessDao.getByBizCode(entity);
            //数据库中不存在
            if( entity1 == null){
                //新增操作
                entity.setAddOperatorId(productVo.getModifyOperatorId());
                productBusinessDao.save(entity);
            }else{
                //修改状态为有效
                entity.setState(BizConstant.INT_ONE);
                productBusinessDao.update(entity);
            }
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
