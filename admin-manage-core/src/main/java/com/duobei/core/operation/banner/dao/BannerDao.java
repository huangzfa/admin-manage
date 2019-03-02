package com.duobei.core.operation.banner.dao;

import com.duobei.core.operation.banner.domain.Banner;
import com.duobei.core.operation.banner.domain.BannerExample;
import com.duobei.core.operation.banner.domain.criteria.BannerCriteria;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/2
 */
public interface BannerDao {
    public int countByCriteria(BannerCriteria criteria);
    public List<Banner> getPage(BannerCriteria criteria);

    Banner getById(Integer id);

    int update(Banner entity);

    List<Banner> queryList(BannerCriteria criteria);

    int save(Banner entity);

    int updateStatus(Banner entity);
}
