package com.duobei.core.operation.consume.dao;

import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/2
 */
public interface ConsumeLoanConfigDao {

    /**
     *
     * @param productId
     * @return
     */
    ConsumeLoanConfig getByProductId(@Param("productId") Integer productId);

    /**
     *
     * @param loanConfig
     * @return
     */
    int save(ConsumeLoanConfig loanConfig);

    /**
     *
     * @param loanConfig
     * @return
     */
    int update(ConsumeLoanConfig loanConfig);

    /**
     * 修改借钱默认页配置
     * @param consumeLoanConfig
     * @return
     */
    int updateBorrowShowById(ConsumeLoanConfig consumeLoanConfig);

    /**
     * 保存借钱默认页配置
     * @param consumeLoanConfig
     * @return
     */
    int saveBorrowShow(ConsumeLoanConfig consumeLoanConfig);

    /**
     *
     * @param id
     * @return
     */
    ConsumeLoanConfig getById(Integer id);
}