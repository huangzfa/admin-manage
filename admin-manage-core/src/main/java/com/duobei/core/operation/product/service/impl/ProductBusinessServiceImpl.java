package com.duobei.core.operation.product.service.impl;

import com.duobei.core.operation.product.dao.ProductBusinessDao;
import com.duobei.core.operation.product.domain.ProductBusiness;
import com.duobei.core.operation.product.service.ProductBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 产品业务表
 * @date 2019/2/26
 */
@Service("ProductBusinessService")
public class ProductBusinessServiceImpl implements ProductBusinessService {

    @Autowired
    private ProductBusinessDao productBusinessDao;

    /**
     *
     * @param productId
     * @return
     */
    @Override
    public List<ProductBusiness> getByProductId(Integer productId){
        return productBusinessDao.getByProductId(productId);
    }
}
