package com.duobei.core.operation.banner.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.banner.domain.Banner;
import com.duobei.core.operation.banner.domain.criteria.BannerCriteria;


/**轮播图service
 * @author litianxiong
 * @date 2019/3/1
 */
public interface BannerService {
    /**
     * 轮播图分页查询
     * @param criteria
     * @return
     */
    ListVo<Banner> getPage(BannerCriteria criteria);

    Banner getById(Integer id);

    void save(Banner entity) throws RuntimeException, TqException;

    void update(Banner entity) throws RuntimeException, TqException;

    void delete(Banner entity) throws RuntimeException, TqException;

    void updateStatus(Banner banner1) throws TqException;
}
