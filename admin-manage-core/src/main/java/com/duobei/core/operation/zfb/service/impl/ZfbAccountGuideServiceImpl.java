package com.duobei.core.operation.zfb.service.impl;

import com.duobei.core.operation.zfb.dao.ZfbAccountGuideDao;
import com.duobei.core.operation.zfb.dao.mapper.ZfbAccountGuideMapper;
import com.duobei.core.operation.zfb.domain.ZfbAccountGuide;
import com.duobei.core.operation.zfb.service.ZfbAccountGuideService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/12
 */
@Service(value = "zfbAccountGuideService")
public class ZfbAccountGuideServiceImpl implements ZfbAccountGuideService {

    @Resource
    ZfbAccountGuideDao zfbAccountGuideDao;

    @Resource
    ZfbAccountGuideMapper zfbAccountGuideMapper;
    @Override
    public List<ZfbAccountGuide> queryZfbAccountGuideByZfbAccountId(Integer id) {
        return zfbAccountGuideDao.queryZfbAccountGuideByZfbAccountId(id);
    }
}
