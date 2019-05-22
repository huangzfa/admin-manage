package com.duobei.core.operation.zfb.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.operation.zfb.domain.criteria.ZfbAccountCriteria;
import com.duobei.core.operation.zfb.domain.vo.ZfbAccountVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface ZfbAccountService {
    ListVo<ZfbAccount> queryZfbAccountList(ZfbAccountCriteria zfbAccountCriteria);

    ZfbAccount queryZfbAccountById(Integer id);
    

    void save(ZfbAccountVo zfbAccountVo) throws TqException;

    void update(ZfbAccountVo zfbAccountVo) throws TqException;

    void deleteZfbAccount(ZfbAccount id) throws TqException;

    void updateStatus(ZfbAccount zfbAccount) throws TqException;

    ZfbAccount getByAccount(String accounts);
}
