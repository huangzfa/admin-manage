package com.duobei.core.operation.banner.impl;


import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.banner.BannerService;
import com.duobei.core.operation.banner.dao.BannerDao;
import com.duobei.core.operation.banner.dao.mapper.BannerMapper;
import com.duobei.core.operation.banner.domain.Banner;
import com.duobei.core.operation.banner.domain.BannerExample;
import com.duobei.core.operation.banner.domain.BannerExample.Criteria;
import com.duobei.core.operation.banner.domain.criteria.BannerCriteria;
import com.duobei.dic.ZD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**轮播图service
 * @author litianxiong
 * @date 2019/3/1
 */
@Service("BannerService")
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDao bannerDao;
    @Resource
    private BannerMapper bannerMapper;

    private static final Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Override
    public ListVo<Banner> getPage(BannerCriteria bannerCriteria){
        BannerExample example = new BannerExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andAppIdEqualTo(bannerCriteria.getAppId());
        /* 统计 */
        //轮播图名称条件
        if (StringUtil.isNotEmpty(bannerCriteria.getTitleName())){
            criteria.andBannerTitleEqualTo(bannerCriteria.getTitleName());
        }
        //轮播图位置条件
        if (StringUtil.isNotEmpty(bannerCriteria.getBannerType())){
            criteria.andBannerTypeEqualTo(bannerCriteria.getBannerType());
        }
        //轮播图状态条件
        if (bannerCriteria.getIsEnable() == null){
            criteria.andIsEnableEqualTo(bannerCriteria.getIsEnable());
        }
        Long total = bannerMapper.countByExample(example);
        List<Banner> bannerList = null;
        if (total > 0) {
            bannerList = bannerDao.queryList(bannerCriteria);
        }
        return new ListVo<Banner>(total.intValue(), bannerList);
   }

    @Override
    public Banner getById(Integer id){
        return bannerDao.getById(id);
    }

    @Override
   // @Transactional(rollbackFor = RuntimeException.class)
    public void save (Banner entity) throws RuntimeException {
        if( bannerDao.save(entity) !=1){
            throw new RuntimeException("banner保存失败");
        }
    }

    @Override
   // @Transactional(rollbackFor = RuntimeException.class)
    public void update(Banner entity) throws RuntimeException{
        if( bannerDao.update(entity) !=1){
            throw new RuntimeException("banner修改失败");
        }
    }

    @Override
  //  @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Banner entity) throws RuntimeException{
        if( bannerDao.update(entity) !=1){
            throw new RuntimeException("banner删除失败");
        }
    }

    @Override
    public void updateStatus(Banner entity) {
        if( bannerDao.updateStatus(entity) !=1){
            throw new RuntimeException("banner修改状态失败");
        }
    }
}
