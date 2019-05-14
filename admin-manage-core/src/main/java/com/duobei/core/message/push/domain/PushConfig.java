package com.duobei.core.message.push.domain;

import java.io.Serializable;
import java.util.Date;

public class PushConfig implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 应用key
     */
    private String appKey;

    /**
     * 账号
     */
    private String apiAccount;

    /**
     * 密码
     */
    private String apiPwd;

    /**
     * 状态: 0.禁用, 1.启用
     */
    private Byte state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private String createUser;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String updateUser;

    /**
     * 
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pub_push_config
     *
     * @mbg.generated Mon May 13 16:50:22 CST 2019
     */
    private static final long serialVersionUID = -2246072203412694221L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount;
    }

    public String getApiPwd() {
        return apiPwd;
    }

    public void setApiPwd(String apiPwd) {
        this.apiPwd = apiPwd;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_push_config
     *
     * @mbg.generated Mon May 13 16:50:22 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appKey=").append(appKey);
        sb.append(", apiAccount=").append(apiAccount);
        sb.append(", apiPwd=").append(apiPwd);
        sb.append(", state=").append(state);
        sb.append(", remark=").append(remark);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}