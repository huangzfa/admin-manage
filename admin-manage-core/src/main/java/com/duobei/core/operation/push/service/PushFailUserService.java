package com.duobei.core.operation.push.service;

import com.duobei.core.operation.push.domain.PushFailUser;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
public interface PushFailUserService {
    /**
     * 查询推送失败名单
     * @param pushId
     * @return
     */
    List<PushFailUser> getListByPushId(Integer pushId);
}
