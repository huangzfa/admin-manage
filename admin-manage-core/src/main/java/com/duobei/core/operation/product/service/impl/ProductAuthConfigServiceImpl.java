package com.duobei.core.operation.product.service.impl;

import com.duobei.core.operation.product.dao.ProductAuthConfigDao;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;
import com.duobei.core.operation.product.service.ProductAuthConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 产品认证项
 * @date 2019/2/26
 */
@Service("ProductAuhConfigService")
public class ProductAuthConfigServiceImpl implements ProductAuthConfigService {

    @Autowired
    private ProductAuthConfigDao productAuhConfigDao;
    /**
     * 根据产品di查询认证项配置
     * @param productId
     * @return
     */
    @Override
    public  List<ProductAuthConfigVo> getByProductId(Integer productId){
        return productAuhConfigDao.getByProductId(productId);
    }
}
