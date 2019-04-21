package com.duobei.core.operation.app.dao;


import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.domain.criteria.AppCriteria;
import com.duobei.core.operation.app.domain.vo.AppVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface AppDao {
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    List<AppVo> getPageList(AppCriteria criteria);

    /**
     * 数量查询
     * @param criteria
     * @return
     */
    int countByCriteria(AppCriteria criteria);

    /**
     * 是否有重复appkey
     * @param app
     * @return
     */
    int countByAppKey(App app);
    /**
     *
     * @param app
     * @return
     */
    int save(App app);

    /**
     *
     * @param app
     * @return
     */
    int update(App app);

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

    /**
     * 根据应用id集合获取map
     * @param appIds
     * @return
     */
    @MapKey("id")
    Map<Integer,App> getMapByIds(@Param("ids") List<Integer> appIds);

    @MapKey("id")
    Map<Integer,App> getMapAll();
}