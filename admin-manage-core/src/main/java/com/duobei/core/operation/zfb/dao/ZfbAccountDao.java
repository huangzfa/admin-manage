package com.duobei.core.operation.zfb.dao;

import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.operation.zfb.domain.criteria.ZfbAccountCriteria;
import com.duobei.core.operation.zfb.domain.vo.ZfbAccountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface ZfbAccountDao {
    List<ZfbAccount> getListByQuery(ZfbAccountCriteria zfbAccountCriteria);

    ZfbAccount getById(Integer id);

    int delete(ZfbAccount id);

    int updateStatus(ZfbAccount zfbAccount);

    int save(ZfbAccount zfbAccount);

    int update(ZfbAccountVo zfbAccountVo);

    int countByIsEnableAndProductId(@Param("productId") Integer productId, @Param("isEnable") int isEnable_yes);

    ZfbAccount getByAccount(@Param("account")  String account);
}
