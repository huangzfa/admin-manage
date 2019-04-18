package com.duobei.core.operation.zfb.service;

import com.duobei.core.operation.zfb.domain.ZfbAccountGuide;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface ZfbAccountGuideService {
    List<ZfbAccountGuide> queryZfbAccountGuideByZfbAccountId(Integer id);
}
