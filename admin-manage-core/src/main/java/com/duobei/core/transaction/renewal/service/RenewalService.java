package com.duobei.core.transaction.renewal.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.transaction.renewal.domain.criteria.BorrowCashRenewalCriteria;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalListVo;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalVo;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
public interface RenewalService {
    /**
     * 列表查询
     * @param renewalCriteria
     * @return
     */
    ListVo<BorrowCashRenewalListVo> getListByQuery(BorrowCashRenewalCriteria renewalCriteria);

    /**
     * 查询详情
     * @param id
     * @return
     */
    BorrowCashRenewalVo getById(Long id);
}
