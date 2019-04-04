package com.duobei.core.operation.bank.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.bank.dao.BankDao;
import com.duobei.core.operation.bank.dao.mapper.BankMapper;
import com.duobei.core.operation.bank.domain.Bank;
import com.duobei.core.operation.bank.domain.BankExample;
import com.duobei.core.operation.bank.domain.criteria.BankCriteria;
import com.duobei.core.operation.bank.service.BankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/19
 */
@Service("bankService")
public class BankServiceImpl implements BankService {
    @Resource
    BankMapper bankMapper;

    @Resource
    BankDao bankDao;

    @Override
    public ListVo<Bank> getListByQuery(BankCriteria bankCriteria) {
        BankExample example = new BankExample();
        BankExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        if (StringUtil.isNotEmpty(bankCriteria.getBankName())){
            criteria.andBankNameEqualTo(bankCriteria.getBankName());
        }
        Long total = bankMapper.countByExample(example);
        List<Bank> data = null;
        if (total > 0){
            data = bankDao.getListByQuery(bankCriteria);
        }
        return new ListVo<>(total.intValue(),data);
    }

    @Override
    public Bank getById(Integer id) {
        return bankDao.getById(id);
    }

    @Override
    public void updateStatus(Bank bank) throws TqException {
        int count = bankDao.updateStatus(bank);
        if (count != 1){
            throw new TqException("修改银行状态失败");
        }
    }

    @Override
    public void update(Bank entity) throws TqException {
        int count = bankDao.update(entity);
        if (count != 1){
            throw new TqException("修改银行信息失败");
        }
    }
}
