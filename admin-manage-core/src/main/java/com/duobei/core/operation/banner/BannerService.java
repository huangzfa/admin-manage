package com.duobei.core.operation.banner;

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

    void save(Banner entity) throws RuntimeException;

    void update(Banner entity) throws RuntimeException;

    void delete(Banner entity) throws RuntimeException;

    void updateStatus(Banner banner1);
}
