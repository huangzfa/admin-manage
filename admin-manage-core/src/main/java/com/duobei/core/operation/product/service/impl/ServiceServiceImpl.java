package com.duobei.core.operation.product.service.impl;

import com.duobei.core.operation.product.dao.ServiceDao;
import com.duobei.core.operation.product.domain.vo.ServiceVo;
import com.duobei.core.operation.product.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/25
 */
@Service("serviceService")
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Override
    public List<ServiceVo> getAll(){
        return serviceDao.getAll();
    }
}
