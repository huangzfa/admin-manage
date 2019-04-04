package com.duobei.core.transaction.renewal.dao;

import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;
import com.duobei.core.transaction.renewal.domain.criteria.BorrowCashRenewalCriteria;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalListVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
public interface BorrowCashRenewalDao {

    /**
     * 查询列表
     * @param renewalCriteria
     * @return
     */
    List<BorrowCashRenewalListVo> getListVoByQuery(BorrowCashRenewalCriteria renewalCriteria);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BorrowCashRenewal getById(Long id);
}
