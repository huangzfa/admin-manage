package com.duobei.core.operation.push.dao;

import com.duobei.core.operation.push.domain.PushFailUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
public interface PushFailUserDao {

    void batchInsert(HashMap<String,Object> maps);

    List<PushFailUser> getListByPushId(Integer pushId);
}
