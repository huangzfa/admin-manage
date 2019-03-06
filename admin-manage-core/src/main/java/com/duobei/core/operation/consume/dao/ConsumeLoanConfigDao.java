package com.duobei.core.operation.consume.dao;

import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import org.apache.ibatis.annotations.Param;

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

}