package com.duobei.core.operation.consume.service;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 消费贷配置
 * @date 2019/3/2
 */
public interface ConsumeLoanConfigService {

    /**
     * 根据产品id查询消费贷配置
     * @param productId
     * @return
     */
    ConsumeLoanConfig getByProductId(Integer productId);

    /**
     * 基础借款配置保存
     * @param record
     * @param auth
     * @throws TqException
     */
    void saveAuth(ConsumeLoanConfig record,List<ProductAuthConfigVo> auth) throws TqException;

    void updateBorrowShowById(ConsumeLoanConfig consumeLoanConfig) throws TqException;

    void saveBorrowShow(ConsumeLoanConfig consumeLoanConfig) throws TqException;

    ConsumeLoanConfig getById(Integer id);

    /**
     * 消费贷相关配置保存
     * @param record
     * @param goodsList
     * @throws TqException
     */
    void saveLoan(ConsumeLoanConfig record,List<ProductConsumdebtGoodsVo> goodsList) throws TqException;
}
