package com.duobei.core.manage.auth.service;

import com.duobei.core.manage.auth.domain.OperatorLoginLog;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;

/**
 * @author huangzhongfa
 * @description
 * @date 2018/12/26
 */
public interface OperatorLoginLogService {

    OperatorLoginLog getByOpId(Integer opId);

    /**
     * 检测是不是第一次登录
     * @param opId
     * @return
     */
    boolean loginFirst(Integer opId);

    void save(OperatorCredential credential, int loginType);
}
