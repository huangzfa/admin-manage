package com.duobei.core.operation.app.dao;


import com.duobei.core.operation.app.domain.App;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface AppDao {

    /**
     * 根据产品id查询APP
     * @param productIds
     * @return
     */
    List<App> getByProductIds(@Param("productIds") List<Integer> productIds);

    /**
     *
     * @return
     */
    List<App> getAll();

    /**
     * 根据Id查询APP信息
     * @param id
     * @return
     */
    App getById(Integer id);
}