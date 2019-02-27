package com.duobei.core.manage.auth.service;

import com.duobei.core.manage.auth.domain.VerifyCodeFail;
import com.duobei.common.exception.TqException;

import java.util.HashMap;

/**
 * @author huangzhognfa
 * @description
 * @date 2018/12/26
 */
public interface VerifyCodeFailService {

    VerifyCodeFail getByParam(HashMap<String,Object> params);

    void save(VerifyCodeFail record) throws TqException;

    void update(VerifyCodeFail record) throws TqException;

}
