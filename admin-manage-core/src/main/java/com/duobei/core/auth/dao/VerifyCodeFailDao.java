package com.duobei.core.auth.dao;

import com.duobei.core.auth.domain.VerifyCodeFail;

import java.util.HashMap;

/**
 * @author huangzhongfa
 * @description
 * @date 2018/12/26
 */
public interface VerifyCodeFailDao {

    VerifyCodeFail getByParam(HashMap<String,Object> params);
}
