package com.duobei.core.operation.consume.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.core.operation.consume.dao.ConsumeLoanConfigDao;
import com.duobei.core.operation.consume.dao.ConsumeLoanRateDayConfigDao;
import com.duobei.core.operation.consume.dao.ConsumeLoanRenewalConfigDao;
import com.duobei.core.operation.consume.dao.mapper.ConsumeLoanConfigMapper;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.consume.domain.ConsumeLoanRateDayConfig;
import com.duobei.core.operation.consume.domain.ConsumeLoanRenewalConfig;
import com.duobei.core.operation.consume.service.ConsumeLoanConfigService;
import com.duobei.core.operation.product.dao.ProductAuthConfigDao;
import com.duobei.core.operation.product.dao.ProductConsumdebtGoodsDao;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;
import com.duobei.core.operation.product.domain.vo.ProductConsumdebtGoodsVo;
import com.duobei.utils.RiskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Autowired
    private ProductConsumdebtGoodsDao productConsumdebtGoodsDao;
    @Autowired
    private ConsumeLoanRenewalConfigDao renewalConfigDao;
    @Autowired
    private ConsumeLoanRateDayConfigDao rateDayConfigDao;
    @Autowired
    private RiskUtil riskUtil;
    @Autowired
    private ProductDao productDao;
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
    /**
     * 消费贷相关配置保存
     * @param record
     * @param goodsList
     * @throws TqException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void saveLoan(ConsumeLoanConfig record,List<ProductConsumdebtGoodsVo> goodsList) throws TqException{
        Product product = productDao.getById(record.getProductId());
        if( product == null ){
            throw new TqException("产品不存在");
        }
        String result = riskUtil.SceneCodeHad(record.getQuotaSceneCode(),product.getId(),product.getMerchantId());
        if ( !result.equals("success")) {
            throw new TqException("额度风控场景编码，原因：" + result);
        }
        result = riskUtil.SceneCodeHad(record.getBorrowSceneCode(),product.getId(),product.getMerchantId());
        if ( !result.equals("success")) {
            throw new TqException("借款风控场景编码-非首次老用户，原因：" + result);
        }
        result = riskUtil.SceneCodeHad(record.getBorrowSceneCodeFirst(),product.getId(),product.getMerchantId());
        if ( !result.equals("success")) {
            throw new TqException("借款风控场景编码-首次新用户校验失败，原因：" + result);
        }
        if( record.getId() == null ){
            if( consumeLoanConfigDao.save(record) <1 ){
                throw new TqException("添加失败");
            }
        }else{
            if( consumeLoanConfigDao.update(record) <1){
                throw new TqException("修改失败");
            }
        }
        //先删除所有记录
        productConsumdebtGoodsDao.updateDelete(record.getProductId());
        for(ProductConsumdebtGoodsVo vo : goodsList){
            if(vo.getId() == null ){
                vo.setAddOperatorId(record.getModifyOperatorId());
                vo.setProductId(record.getProductId());
                if( productConsumdebtGoodsDao.save(vo) <1){
                    throw  new TqException("借贷商品保存失败");
                }
            }else{
                vo.setModifyOperatorId(record.getModifyOperatorId());
                vo.setModifyTime(new Date());
                vo.setIsDelete(BizConstant.INT_ZERO);
                if( productConsumdebtGoodsDao.update(vo) <1){
                    throw  new TqException("借贷商品保存失败");
                }
            }
        }
    }
    /**
     * 利率期限配置
     * @param record
     * @param rateDayList
     * @param renewalsList
     * @throws TqException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void rateDaySave(ConsumeLoanConfig record, List<ConsumeLoanRateDayConfig> rateDayList, List<ConsumeLoanRenewalConfig> renewalsList)throws TqException{
        if( record.getId() == null ){
            if( consumeLoanConfigDao.save(record) <1 ){
                throw new TqException("添加失败");
            }
        }else{
            if( consumeLoanConfigDao.update(record) <1){
                throw new TqException("修改失败");
            }
        }
        for( ConsumeLoanRateDayConfig config :rateDayList){
            config.setConsumeLoanConfigId(record.getId());
        }
        //第一个id == 0，执行插入操作，否则修改
        if( rateDayList.get(BizConstant.INT_ZERO).getId().equals(BizConstant.INT_ZERO)){
            if( rateDayConfigDao.batchSave(rateDayList) < 1){
                throw new TqException("借款利率对应天数配置添加失败");
            }
        }else{
            if( rateDayConfigDao.batchUpdate(rateDayList) <1){
                throw new TqException("借款利率对应天数配置修改失败");
            }
        }
        for(ConsumeLoanRenewalConfig config :renewalsList){
            config.setConsumeLoanConfigId(record.getId());
            if( config.getId().equals(BizConstant.INT_ZERO)){
                if( renewalConfigDao.save(config) < 1){
                    throw new TqException("未逾期配置添加失败");
                }
            }else{
                if(renewalConfigDao.update(config)<0){
                    throw new TqException("未逾期配置修改失败");
                }
            }
        }
    }

    /**
     *
     * @param consumeLoanConfig
     * @throws TqException
     */
    @Override
    public void updateBorrowShowById(ConsumeLoanConfig consumeLoanConfig) throws TqException {
        int count = consumeLoanConfigDao.updateBorrowShowById(consumeLoanConfig);
        if (count != -1){
            throw new TqException("借钱默认页修改失败");
        }
    }

    /**
     *
     * @param consumeLoanConfig
     * @throws TqException
     */
    @Override
    public void saveBorrowShow(ConsumeLoanConfig consumeLoanConfig) throws TqException {
        int count = consumeLoanConfigDao.saveBorrowShow(consumeLoanConfig);
        if (count != -1){
            throw new TqException("借钱默认页保存失败");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ConsumeLoanConfig getById(Integer id){
        return consumeLoanConfigDao.getById(id);
    }

}
