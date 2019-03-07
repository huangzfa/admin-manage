package com.duobei.core.operation.product.service.impl;

import com.duobei.core.operation.product.dao.ProductConsumdebtGoodsDao;
import com.duobei.core.operation.product.domain.vo.ProductConsumdebtGoodsVo;
import com.duobei.core.operation.product.service.ProductConsumdebtGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/7
 */
@Service("ProductConsumdebtGoodsService")
public class ProductConsumdebtGoodsServiceImpl implements ProductConsumdebtGoodsService{

    @Autowired
    private ProductConsumdebtGoodsDao productConsumdebtGoodsDao;
    /**
     * 根据产品id查询关联的商品
     * @param productId
     * @return
     */
    @Override
   public List<ProductConsumdebtGoodsVo> getByProductId(Integer productId){
        return productConsumdebtGoodsDao.getByProductId(productId);
   }
}
