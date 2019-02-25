package com.duobei.core.auth.service.impl;

import com.duobei.core.auth.dao.OperatorLoginLogDao;
import com.duobei.core.auth.dao.mapper.OperatorLoginLogMapper;
import com.duobei.core.auth.domain.OperatorLoginLog;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.service.OperatorLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2018/12/26
 */
@Service("OperatorLoginLogService")
public class OperatorLoginLogServiceImpl implements OperatorLoginLogService {

    @Autowired
    private OperatorLoginLogDao operatorLoginLogDao;
    @Autowired
    private OperatorLoginLogMapper operatorLoginLogMapper;

    @Override
    public OperatorLoginLog getByOpId(Integer opId) {
        return operatorLoginLogDao.getByOpId(opId);
    }

    @Override
    public void save(OperatorCredential credential, int loginType) {
        OperatorLoginLog oll = new OperatorLoginLog();
        oll.setOpId(credential.getOpId());
        oll.setSessionId(String.valueOf(credential.getSessionId()));
        oll.setLoginType(loginType + "");// 1-登录，0-登出
        oll.setIp(credential.getIp());
        oll.setAddTime(new Date());
        operatorLoginLogMapper.insertSelective(oll);
    }

    /**
     * 检测是不是第一次登录
     * @param opId
     * @return
     */
    @Override
    public boolean loginFirst(Integer opId){
        OperatorLoginLog log = operatorLoginLogDao.getByOpId(opId);
        if( log == null ){
            return true;
        }else{
            return false;
        }
    }
}
