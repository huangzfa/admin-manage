package com.duobei.core.operation.bank.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.bank.domain.Bank;
import com.duobei.core.operation.bank.domain.criteria.BankCriteria;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/19
 */
public interface BankService {
    /**
     * 根据条件获取集合
     * @param bankCriteria
     * @return
     */
    ListVo<Bank> getListByQuery(BankCriteria bankCriteria);

    /**
     * 根据id获取银行卡信息
     * @param id
     * @return
     */
    Bank getById(Integer id);

    /**
     * 修改状态
     * @param bank
     */
    void updateStatus(Bank bank) throws TqException;

    /**
     * 修改银行卡信息
     * @param entity
     */
    void update(Bank entity) throws TqException;
}
