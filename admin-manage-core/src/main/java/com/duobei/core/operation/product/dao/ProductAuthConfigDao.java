package com.duobei.core.operation.product.dao;


import com.duobei.core.operation.product.domain.ProductAuthConfig;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description 产品认证项
 * @date 2019/2/26
 */
public interface ProductAuthConfigDao {

    int update(ProductAuthConfig record);

    int save(ProductAuthConfig record);

    List<ProductAuthConfigVo> getByProductId(@Param("productId") Integer productId);
}
