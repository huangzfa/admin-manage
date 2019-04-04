package com.duobei.core.operation.consume.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.core.operation.consume.dao.ConsumeLoanConfigDao;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.consume.service.ConsumeLoanConfigService;
import com.duobei.core.operation.product.dao.ProductAuthConfigDao;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/2
 */
@Service("ConsumeLoanConfigService")
public class ConsumeLoanConfigServiceImpl implements ConsumeLoanConfigService {

    @Autowired
    private ConsumeLoanConfigDao consumeLoanConfigDao;
    @Autowired
    private ProductAuthConfigDao productAuthConfigDao;
    /**
     * 根据产品id查询消费贷配置
     * @param productId
     * @return
     */
    @Override
    public ConsumeLoanConfig getByProductId(Integer productId){
        return consumeLoanConfigDao.getByProductId(productId);
    }

    /**
     * 基础借款配置
     * @param record
     * @param auth
     * @throws TqException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void saveAuth(ConsumeLoanConfig record,List<ProductAuthConfigVo> auth) throws TqException{
        if( record.getId() == null ){
            if( consumeLoanConfigDao.save(record) <1 ){
                throw new TqException("添加失败");
            }
        }else{
            if( consumeLoanConfigDao.update(record) <1){
                throw new TqException("修改失败");
            }
        }
        //认证项保存
        for(ProductAuthConfigVo vo : auth){
            if( vo.getValidVal() == null){
                throw  new TqException("请填写认证有效期");
            }
            if(vo.getId() == null ){
                vo.setAddOperatorId(record.getModifyOperatorId());
                vo.setIsEnable(BizConstant.INT_ONE);
                vo.setProductId(record.getProductId());
                if( productAuthConfigDao.save(vo) <1){
                    throw  new TqException("认证项保存失败");
                }
            }else{
                vo.setModifyOperatorId(record.getModifyOperatorId());
                vo.setModifyTime(new Date());
                if( productAuthConfigDao.update(vo) <1){
                    throw  new TqException("认证项修改失败");
                }
            }
        }
    }

    @Override
    public void updateBorrowShowById(ConsumeLoanConfig consumeLoanConfig) throws TqException {
        int count = consumeLoanConfigDao.updateBorrowShowById(consumeLoanConfig);
        if (count != -1){
            throw new TqException("借钱默认页修改失败");
        }
    }

    @Override
    public void saveBorrowShow(ConsumeLoanConfig consumeLoanConfig) throws TqException {
        int count = consumeLoanConfigDao.saveBorrowShow(consumeLoanConfig);
        if (count != -1){
            throw new TqException("借钱默认页保存失败");
        }
    }


}
