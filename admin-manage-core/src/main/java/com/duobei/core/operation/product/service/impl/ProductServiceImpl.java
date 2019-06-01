package com.duobei.core.operation.product.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.encrypt.MD5Util;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.BusinessServiceDao;
import com.duobei.core.operation.product.dao.ProductBusinessDao;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Business;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.ProductBusiness;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.BusinessServiceVo;
import com.duobei.core.operation.product.domain.vo.ProductBusinessVo;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import com.duobei.core.operation.product.service.MerchantService;
import com.duobei.core.operation.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Slf4j
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductBusinessDao productBusinessDao;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private BusinessServiceDao businessServiceDao;



    /**
     * 分页查询
     *
     * @param criteria
     * @return
     */
    @Override
    public ListVo<ProductVo> getLists(ProductCriteria criteria) {
        //搜索服务查询
        if( StringUtil.isBlank(criteria.getServiceCode())){
            //查询服务关联了哪些业务
            List<BusinessServiceVo> list = businessServiceDao.getByServiceCode(criteria.getServiceCode());
            List<String> bizCodes =  list.stream().map(BusinessServiceVo::getBizCode).collect(Collectors.toList());
            //查询哪些产品关联了该业务
            List<ProductBusiness> products = productBusinessDao.getByBizCodes(bizCodes);
            List<Integer> productList = products.stream().map(ProductBusiness::getProductId).collect(Collectors.toList());
            criteria.setProductList(productList);
        }
        int total = productDao.countByCriteria(criteria);
        List<ProductVo> list = null;
        if (total > 0) {
            list = productDao.getPageList(criteria);
            //查询业务
            List<Integer> ids = list.stream().map(ProductVo::getId).collect(Collectors.toList());
            List<ProductBusinessVo> businessVos = productBusinessDao.getByProductIds(ids);
            for( ProductVo vo : list){
                String bizName = "";
                String bizCodes="";
                for (ProductBusinessVo businessVo :businessVos){
                    if( businessVo.getState().equals(BizConstant.INT_ONE) && vo.getId().equals(businessVo.getProductId())){
                        bizName+=","+businessVo.getBizName();
                        bizCodes+=","+businessVo.getBizCode();
                    }
                }
                if(!StringUtil.isEmpty(bizName)){
                    vo.setBizName(bizName.substring(1));
                    vo.setBizCodes(bizCodes.substring(1));
                }
            }
            //查询接入服务
            List<BusinessServiceVo> businessServiceVoList = businessServiceDao.getByBizCode(null);
            for( ProductVo vo : list){
                String serviceName = "";
                for(BusinessServiceVo serviceVo :businessServiceVoList){
                    if(!StringUtil.isBlank(vo.getBizCodes()) && vo.getBizCodes().contains(serviceVo.getBizCode())){
                        if( !serviceName.contains(serviceVo.getServiceName())){
                            serviceName+=","+serviceVo.getServiceName();
                        }
                    }
                }
                if(!StringUtil.isEmpty(serviceName)){
                    vo.setServiceName(serviceName.substring(1));
                }
            }
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
        merchantService.noticeByProduct(productVo);
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
        merchantService.noticeByProduct(productVo);
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
        merchantService.noticeByProduct(productVo);

    }
    /**
     * 修改产品
     * @param product
     */
    @Override
    public void update(ProductVo  product) throws TqException{
        if( productDao.update(product) <1){
            throw new TqException("添加失败");
        }
        merchantService.noticeByProduct(product);
    }

    /**
     * 添加产品
     * @param product
     */
    @Override
    public void save(ProductVo  product) throws TqException{
        if( productDao.update(product) <1){
            throw new TqException("修改失败");
        }
        merchantService.noticeByProduct(product);
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
