package com.duobei.core.operation.product.dao;

import com.duobei.core.operation.product.domain.BusinessService;
import com.duobei.core.operation.product.domain.vo.BusinessServiceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/25
 */
public interface BusinessServiceDao {
    List<BusinessServiceVo> getByBizCode(@Param("bizCode") String bizCode);

    List<BusinessServiceVo> getByServiceCode(@Param("serviceCode") String servieCode);

    int save(BusinessService record);

    void delByBizCode(@Param("bizCode") String bizCode);
}
