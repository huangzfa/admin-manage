package com.duobei.core.operation.app.service.impl;

import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Slf4j
@Service("AppService")
public class AppServiceImpl implements AppService{

    @Autowired
    private AppDao appDao;

    /**
     * 根据产品查询app
     * @param list
     * @return
     */
    @Override
    public List<App> getByProductIds(List<Product> list){
        if( list == null || list.size() == 0){
            return null;
        }
        List<Integer> productIds = list.stream().map(Product::getId).collect(Collectors.toList());
        return appDao.getByProductIds(productIds);
    }
}
