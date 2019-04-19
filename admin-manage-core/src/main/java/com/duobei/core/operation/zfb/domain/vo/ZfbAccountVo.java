package com.duobei.core.operation.zfb.domain.vo;

import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.operation.zfb.domain.ZfbAccountGuide;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public class ZfbAccountVo extends ZfbAccount {
    private List<ZfbAccountGuide> zfbAccountGuideList;

    private String imgUrls;

    public List<ZfbAccountGuide> getZfbAccountGuideList() {
        return zfbAccountGuideList;
    }

    public void setZfbAccountGuideList(List<ZfbAccountGuide> zfbAccountGuideList) {
        this.zfbAccountGuideList = zfbAccountGuideList;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }
}
