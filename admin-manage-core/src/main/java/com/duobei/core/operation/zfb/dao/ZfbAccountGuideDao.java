package com.duobei.core.operation.zfb.dao;

import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.operation.zfb.domain.ZfbAccountGuide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface ZfbAccountGuideDao {
    void deleteOldGuideByAccount(ZfbAccount zfbAccount);

    int saveList(@Param("guideList") List<ZfbAccountGuide> guideList );

    List<ZfbAccountGuide> queryZfbAccountGuideByZfbAccountId(Integer id);
}
