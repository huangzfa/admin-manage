package com.duobei.core.transaction.repayment.dao;

import com.duobei.core.transaction.repayment.domain.RepaymentOffline;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentOfflineCriteria;
import com.duobei.core.transaction.repayment.domain.vo.RepaymentOfflineVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/20
 */
public interface RepaymentOfflineDao {
    RepaymentOffline getByBorrowId(@Param("borrowId") Long borrowId);

    List<RepaymentOfflineVo> getPage(RepaymentOfflineCriteria criteria);

    int countByCriteria(RepaymentOfflineCriteria criteria);

    RepaymentOffline getById(@Param("id") Long id);
}
