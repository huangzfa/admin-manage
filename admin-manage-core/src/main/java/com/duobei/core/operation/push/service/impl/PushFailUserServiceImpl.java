package com.duobei.core.operation.push.service.impl;

import com.duobei.core.operation.push.dao.PushFailUserDao;
import com.duobei.core.operation.push.domain.PushFailUser;
import com.duobei.core.operation.push.service.PushFailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
@Service("pushFailUserService")
public class PushFailUserServiceImpl implements PushFailUserService {

    @Autowired
    private PushFailUserDao failUserDao;

    /**
     * 查询推送失败名单
     * @param pushId
     * @return
     */
    @Override
    public  List<PushFailUser> getListByPushId(Integer pushId){
        return  failUserDao.getListByPushId(pushId);
    }
}
