package com.duobei.core.operation.product.service;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.product.domain.vo.BusinessServiceVo;

import java.util.List;

/**业务服务中间表服务service
 * @author huangzhongfa
 * @description
 * @date 2019/4/25
 */
public interface BusinessServiceService {
    /**
     * 根据业务编码查询关联服务
     * @param bizCode
     * @return
     */
    List<BusinessServiceVo> getByBizCode(String bizCode);

    /**
     *
     * @param bizCode
     * @param serviceCodes
     * @param addUserId
     * @throws TqException
     */
    void save(String bizCode,String serviceCodes,Integer addUserId) throws TqException;
}
