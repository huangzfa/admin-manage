package com.duobei.core.operation.app.service.impl;

import com.duobei.common.config.Global;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.dao.mapper.OperatorMapper;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.operation.app.dao.AppExamineDao;
import com.duobei.core.operation.app.dao.mapper.AppExamineMapper;
import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.AppExamineExample;
import com.duobei.core.operation.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.operation.app.domain.vo.AppExamineVo;
import com.duobei.core.operation.app.service.AppExamineService;
import com.duobei.core.operation.channel.dao.PromotionChannelDao;
import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import com.duobei.core.operation.channel.service.PromotionChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
@Service("appExamineService")
public class AppExamineServiceImpl implements AppExamineService {

    @Resource
    AppExamineMapper appExamineMapper;
    @Resource
    AppExamineDao appExamineDao;

    @Resource
    PromotionChannelDao promotionChannelDao;

    /**
     * 分页查询
     * @param appExamineCriteria
     * @return
     */
    @Override
    public ListVo<AppExamineVo> getListVoByQuery(AppExamineCriteria appExamineCriteria) throws TqException{
        AppExamineExample example = new AppExamineExample();
        AppExamineExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0L);
        if (appExamineCriteria.getAppId() != null){
            criteria.andAppIdEqualTo(appExamineCriteria.getAppId());
        }else{
            criteria.andAppIdIn(appExamineCriteria.getAppIds());
        }
        if (StringUtil.isNotEmpty(appExamineCriteria.getChannelName())){
             List<Integer> channelIds = promotionChannelDao.getAppChannelIdListByName(appExamineCriteria.getChannelName());
            criteria.andChannelIdIn(channelIds);
            appExamineCriteria.setChannelIds(channelIds);
        }
        if (appExamineCriteria.getVersionNumber() != null){
            criteria.andVersionNumberEqualTo(appExamineCriteria.getVersionNumber());
        }

        Long total = appExamineMapper.countByExample(example);
        List<AppExamineVo> appExamineVos = null;
        if (total > 0) {
            appExamineVos = appExamineDao.getListVoByQuery(criteria);
        }

        return new ListVo<AppExamineVo>(total.intValue(),appExamineVos);


    }

    @Override
    public AppExamine getById(Integer id) {
        return appExamineMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(AppExamine appExamine) throws TqException {
        int count = appExamineDao.delete(appExamine);
        if (count != 1){
            throw new TqException("删除app审核失败");
        }
    }


}
