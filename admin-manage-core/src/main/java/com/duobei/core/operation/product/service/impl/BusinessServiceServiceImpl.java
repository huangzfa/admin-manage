package com.duobei.core.operation.product.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.core.operation.product.dao.BusinessServiceDao;
import com.duobei.core.operation.product.domain.BusinessService;
import com.duobei.core.operation.product.domain.vo.BusinessServiceVo;
import com.duobei.core.operation.product.service.BusinessServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/25
 */
@Service("businessServiceService")
public class BusinessServiceServiceImpl implements BusinessServiceService {

    @Autowired
     private BusinessServiceDao businessServiceDao;

    /**
     * 根据业务编码查询关联服务
     * @param bizCode
     * @return
     */
    @Override
    public List<BusinessServiceVo> getByBizCode(String bizCode){
        return businessServiceDao.getByBizCode(bizCode);
    }


    /**
     *
     * @param bizCode
     * @param serviceCodes
     * @param addUserId
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void save(String bizCode,String serviceCodes,Integer addUserId){
        //删除原来所有绑定的服务
        businessServiceDao.delByBizCode(bizCode);
        if(StringUtil.isNotEmpty(serviceCodes)){
            String[] serviceCode = serviceCodes.split(",");
            for(String code:serviceCode){
                BusinessService record = new BusinessService();
                record.setAddOperatorId(addUserId);
                record.setBizCode(bizCode);
                record.setServiceCode(code);
                businessServiceDao.save(record);
            }
        }
    }

}
