package com.duobei.core.jpush.domain;

public class PushMsgModel {
    /**
     *1、自定义消息 2、通知
     */
    private Integer pushType;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 1、安卓2、IOS（正式）3、IOS测试
     */
    private String  plateformData;
    /**
     * 推送人员 1、所有人 2、用户id
     */
    private Integer pushUser;
    /**
     * 推送名单
     */
    private String userNames;
    /**
     *
     */
    private Integer offLineSaveTime;
    /**
     *
     */
    private Integer pushTimeConfig;
    /**
     *
     */
    private Integer setTiming = 0;

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
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

    public String getPlateformData() {
        return plateformData;
    }

    public void setPlateformData(String plateformData) {
        this.plateformData = plateformData;
    }

    public Integer getPushUser() {
        return pushUser;
    }

    public void setPushUser(Integer pushUser) {
        this.pushUser = pushUser;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public Integer getOffLineSaveTime() {
        return offLineSaveTime;
    }

    public void setOffLineSaveTime(Integer offLineSaveTime) {
        this.offLineSaveTime = offLineSaveTime;
    }

    public Integer getPushTimeConfig() {
        return pushTimeConfig;
    }

    public void setPushTimeConfig(Integer pushTimeConfig) {
        this.pushTimeConfig = pushTimeConfig;
    }

    public Integer getSetTiming() {
        return setTiming;
    }

    public void setSetTiming(Integer setTiming) {
        this.setTiming = setTiming;
    }
}
