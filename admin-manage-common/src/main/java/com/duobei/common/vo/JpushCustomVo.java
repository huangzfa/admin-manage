package com.duobei.common.vo;

/**
 * 推送自定义对象
 */
public class  JpushCustomVo {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     *  1-消息 2-通知
     */
    private int pushType;
    /**
     *  额外参数（JSON对象）
     */
    private String extraJson;
    /**
     * 用户别名
     */
    private String[] alias;
    /**
     * 极光注册Id
     */
    private String[] registIds;
    /**
     * 是否全部
     */
    private Boolean all;
    /**
     * 推送平台
     */
    private String[] plateforms;
    /**
     * 定速推送时长 -1为不设置，默认不开启
     */
    private Integer setTiming;

    /**
     * 离线保存时间  -1为不设置 默认为1天
     */
    private Long timeToLive;

    private String systemCode;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String[] getPlateforms() {
        return plateforms;
    }

    public void setPlateforms(String[] plateforms) {
        this.plateforms = plateforms;
    }

    public Integer getSetTiming() {
        return setTiming;
    }

    public void setSetTiming(Integer setTiming) {
        this.setTiming = setTiming;
    }

    public Long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
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

    public String[] getRegistIds() {
        return registIds;
    }

    public void setRegistIds(String[] registIds) {
        this.registIds = registIds;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }
}
