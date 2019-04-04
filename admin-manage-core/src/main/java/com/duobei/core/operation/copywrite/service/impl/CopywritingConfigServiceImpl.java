package com.duobei.core.operation.copywrite.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.copywrite.domain.criteria.CopywritingConfigCriteria;
import com.duobei.core.operation.copywrite.dao.CopywritingConfigDao;
import com.duobei.core.operation.copywrite.dao.mapper.CopywritingConfigMapper;
import com.duobei.core.operation.copywrite.domain.CopywritingConfig;
import com.duobei.core.operation.copywrite.domain.CopywritingConfigExample;
import com.duobei.core.operation.copywrite.service.CopywritingConfigService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/20
 */
@Service("copywritingConfigService")
public class CopywritingConfigServiceImpl implements CopywritingConfigService {
    @Resource
    CopywritingConfigMapper copywritingConfigMapper;

    @Resource
    CopywritingConfigDao copywritingConfigDao;

    @Override
    public ListVo<CopywritingConfig> getListByQuery(CopywritingConfigCriteria configCriteria) {
        CopywritingConfigExample example = new CopywritingConfigExample();
        CopywritingConfigExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andProductIdEqualTo(configCriteria.getProductId());
        //统计
        Long total = copywritingConfigMapper.countByExample(example);
        //如果数量>0 则查询数据
        List<CopywritingConfig> data = null;
        if (total > 0){
            data = copywritingConfigDao.getListByQuery(configCriteria);
        }
        return new ListVo<>(total.intValue(),data);
    }

    @Override
    public CopywritingConfig getById(Integer id) {
        return copywritingConfigDao.getById(id);
    }

    @Override
    public void update(CopywritingConfig entity) throws TqException {
        int count = copywritingConfigDao.update(entity);
        if (count != 1){
            throw new TqException("文案配置修改失败");
        }
    }
}
