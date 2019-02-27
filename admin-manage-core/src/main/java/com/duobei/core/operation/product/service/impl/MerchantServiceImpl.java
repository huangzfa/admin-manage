package com.duobei.core.operation.product.service.impl;

import com.duobei.common.datasource.DataSourceConst;
import com.duobei.common.datasource.DataSourceHandle;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.MerchantDao;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.criteria.MerchantCriteria;
import com.duobei.core.operation.product.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@Service("MerchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDao merchantDao;
    /**
     * 分页查询
     * @return
     */
    @Override
    public ListVo<Merchant> getPageList(MerchantCriteria criteria){
        DataSourceHandle.setDataSourceType(DataSourceConst.OPERATE);
        int total = merchantDao.countByCriteria(criteria);
        List<Merchant> list = null;
        if (total > 0) {
            list = merchantDao.getPageList(criteria);
        }
        DataSourceHandle.clearDataSourceType();
        return new ListVo<Merchant>(total, list);
    }

    /**
     * 查询所有有效记录
     * @return
     */
    @Override
    public List<Merchant> getAll(){
        return merchantDao.getAll();
    }

    /**
     * 修改商户
     * @param merchant
     */
    @Override
    public void update(Merchant merchant){

    }

    /**
     * 添加商户
     * @param merchant
     */
    @Override
    public void save(Merchant merchant){

    }
}
