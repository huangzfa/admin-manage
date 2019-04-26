package com.duobei.core.operation.product.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.BusinessDao;
import com.duobei.core.operation.product.domain.Business;
import com.duobei.core.operation.product.domain.criteria.BusinessCriteria;
import com.duobei.core.operation.product.domain.vo.BusinessVo;
import com.duobei.core.operation.product.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/5
 */
@Service("BusinessService")
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;
    /**
     *
     * @return
     */
    @Override
    public List<BusinessVo> getAll(){
        return businessDao.getAll();
    }

    @Override
    public ListVo<BusinessVo> getPageList(){
        List<BusinessVo> list = businessDao.getPageList();
        return new ListVo<BusinessVo>(list.size(), list);
    }
}
