package com.duobei.common.vo;



public class JpushVo {
    /**
     * 推送模板id
     */
    private String pushTempletCode;

    /**
     * 模板替换值JSON
     */
    private String paramsJson;

    /**
     * 额外参数json
     */
    private String extraJson;

    /**
     *  用户别名
     */
    private String[] alias;
    /**
     * 是否全部推送 默认 false
     */
    private Boolean isAll = false;

    /**
     * 项目编码
     */
    private String systemCode;

    private String content;

    public String getPushTempletCode() {
        return pushTempletCode;
    }

    public void setPushTempletCode(String pushTempletCode) {
        this.pushTempletCode = pushTempletCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getParamsJson() {
        return paramsJson;
    }

    public void setParamsJson(String paramsJson) {
        this.paramsJson = paramsJson;
    }

    public String getExtraJson() {
        return extraJson;
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public Boolean getAll() {
        return isAll;
    }

    public void setAll(Boolean all) {
        isAll = all;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
