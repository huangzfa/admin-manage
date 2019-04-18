package com.duobei.core.user.user.domain.vo;

import com.duobei.core.user.user.domain.User;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
public class UserListVo extends User {

    private String productName;

    private UserLoginLastLogVo lastLogVo;

    /**
     * 身份证翻拍照姓名-脱敏
     */
    private String realName;

    /**
     * 身份证翻拍照姓名密文无解，用于匹配
     */
    private String realNameMd5;

    /**
     * 身份证翻拍照姓名密文可解
     */

    private String realNameEncrypt;

    public UserLoginLastLogVo getLastLogVo() {
        return lastLogVo;
    }

    public void setLastLogVo(UserLoginLastLogVo lastLogVo) {
        this.lastLogVo = lastLogVo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealNameMd5() {
        return realNameMd5;
    }

    public void setRealNameMd5(String realNameMd5) {
        this.realNameMd5 = realNameMd5;
    }

    public String getRealNameEncrypt() {
        return realNameEncrypt;
    }

    public void setRealNameEncrypt(String realNameEncrypt) {
        this.realNameEncrypt = realNameEncrypt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
