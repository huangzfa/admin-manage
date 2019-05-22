package com.duobei.core.transaction.repayment.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.transaction.repayment.domain.RepaymentOffline;
import com.duobei.core.transaction.repayment.domain.bo.RepaymentOfflineBo;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentOfflineCriteria;
import com.duobei.core.transaction.repayment.domain.vo.RepaymentOfflineVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/20
 */
public interface RepaymentOfflineService {

    ListVo<RepaymentOfflineVo> getPage(RepaymentOfflineCriteria criteria) throws TqException;

    RepaymentOffline getById(Long id);

    /**
     * 单个平账
     */
    void flatAccount(RepaymentOfflineBo bo) throws TqException;


    /**
     * 批量平账
     * @param list
     */
    HashMap<String,Object> batchFlatAccount(List<RepaymentOfflineBo> list, ZfbAccount account,OperatorCredential credential);
}
