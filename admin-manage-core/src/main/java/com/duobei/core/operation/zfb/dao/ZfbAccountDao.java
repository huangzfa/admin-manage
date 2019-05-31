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

    /**
     * 同产品是否有相同的支付宝账号
     * @return
     */
    int countByAccount(ZfbAccount zfbAccount);

    ZfbAccount getByAccount(@Param("account")  String account);
}
