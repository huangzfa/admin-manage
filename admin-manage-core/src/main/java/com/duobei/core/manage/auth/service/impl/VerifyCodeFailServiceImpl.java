package com.duobei.core.manage.auth.service.impl;

import com.duobei.core.manage.auth.dao.VerifyCodeFailDao;
import com.duobei.core.manage.auth.dao.mapper.VerifyCodeFailMapper;
import com.duobei.core.manage.auth.domain.VerifyCodeFail;
import com.duobei.core.manage.auth.service.VerifyCodeFailService;
import com.duobei.common.exception.TqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author huangzhongfa
 * @description
 * @date 2018/12/26
 */
@Service("VerifyCodeFailService")
public class VerifyCodeFailServiceImpl implements VerifyCodeFailService {

    @Autowired
    private VerifyCodeFailDao verifyCodeFailDao;
    @Autowired
    private VerifyCodeFailMapper verifyCodeFailMapper;
    @Override
    public VerifyCodeFail getByParam(HashMap<String,Object> params){

       return verifyCodeFailDao.getByParam(params);
    }

    @Override
    public void save(VerifyCodeFail record) throws TqException {
        if(verifyCodeFailMapper.insert(record)!=1){
            throw new TqException("系统异常");
        }
    }

    @Override
    public void update(VerifyCodeFail record) throws TqException{
        if(verifyCodeFailMapper.updateByPrimaryKeySelective(record)!=1){
            throw new TqException("系统异常");
        }
    }
}
