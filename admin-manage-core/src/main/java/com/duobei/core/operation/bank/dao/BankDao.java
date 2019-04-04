package com.duobei.core.operation.bank.dao;

import com.duobei.core.operation.bank.domain.Bank;
import com.duobei.core.operation.bank.domain.BankExample;
import com.duobei.core.operation.bank.domain.criteria.BankCriteria;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/19
 */
public interface BankDao {
    List<Bank> getListByQuery(BankCriteria criteria);

    Bank getById(Integer id);

    int updateStatus(Bank bank);

    int update(Bank entity);
}
