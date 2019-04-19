package com.duobei.core.user.user.domain.criteria;

import com.duobei.common.util.Pagination;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.user.user.domain.User;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public class UserCriteria extends Pagination {
    /**
     * 用户姓名
     */
    private String realName;

    private List<Long> userIds;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 注册手机
     */
    private String userName;

    /**
     * 全局id
     */

    private String globalUserId;

    private List<Product> productList;

    private List<App> appList;

    public List<App> getAppList() {
        return appList;
    }

    public void setAppList(List<App> appList) {
        this.appList = appList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getGlobalUserId() {
        return globalUserId;
    }

    public void setGlobalUserId(String globalUserId) {
        this.globalUserId = globalUserId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
