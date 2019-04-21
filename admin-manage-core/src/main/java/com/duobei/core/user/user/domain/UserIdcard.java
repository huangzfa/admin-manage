package com.duobei.core.user.user.domain;

import java.io.Serializable;
import java.util.Date;

public class UserIdcard implements Serializable {
    /**
     * 主键，自增id
     */
    private Long id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 用户id
     */
    private Long userId;

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

    /**
     * 身份证翻拍照身份证号-脱敏
     */
    private String idcard;

    /**
     * 身份证翻拍照身份证号密文无解，用于匹配
     */
    private String idcardMd5;

    /**
     * 身份证翻拍照身份证号密文可解
     */
    private String idcardEncrypt;

    /**
     * 手动输入的姓名-脱敏
     */
    private String editName;

    /**
     * 手动输入的姓名密文无解，用于匹配
     */
    private String editNameMd5;

    /**
     * 手动输入的姓名密文可解
     */
    private String editNameEncrypt;

    /**
     * 手动输入的身份证号
     */
    private String editIdcard;

    /**
     * 手动输入的身份证号密文无解，用于匹配
     */
    private String editIdcardMd5;

    /**
     * 手动输入的身份证号密文可解
     */
    private String editIdcardEncrypt;

    /**
     * 地址
     */
    private String address;

    /**
     * 性别【1：男，0：女，2：未知】，字典
     */
    private Integer gender;

    /**
     * 民族
     */
    private String nation;

    /**
     * 身份证有效期 开始时间
     */
    private String validBegin;

    /**
     * 身份证有效期 结束时间
     */
    private String validEnd;

    /**
     * 出生年月日
     */
    private String birthday;

    /**
     * 签发机关
     */
    private String agency;

    /**
     * 身份证正面照片
     */
    private String idcardFrontUrl;

    /**
     * 身份证反面照片
     */
    private String idcardBehindUrl;

    /**
     * 人脸识别图片
     */
    private String faceUrl;

    /**
     * 识别三方：0:依图，1:face++
     */
    private String faceType;

    /**
     * 图片是否下载(0:未下载 1:已下载 -1:下载失败)(主要针对第三方平台身份证和人脸图片)
     */
    private String downloadPic;

    /**
     * 人脸识别图片 - 半身照
     */
    private String faceEnvUrl;

    /**
     * 人脸识别图片 - 动图1
     */
    private String faceAct1Url;

    /**
     * 人脸识别图片 - 动图2
     */
    private String faceAct2Url;

    /**
     * 人脸识别图片 - 动图3
     */
    private String faceAct3Url;

    /**
     * 删除标志，0有效，其他值无效
     */
    private Long isDelete;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pgy_user_idcard
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    private static final long serialVersionUID = 576785791251914889L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardMd5() {
        return idcardMd5;
    }

    public void setIdcardMd5(String idcardMd5) {
        this.idcardMd5 = idcardMd5;
    }

    public String getIdcardEncrypt() {
        return idcardEncrypt;
    }

    public void setIdcardEncrypt(String idcardEncrypt) {
        this.idcardEncrypt = idcardEncrypt;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public String getEditNameMd5() {
        return editNameMd5;
    }

    public void setEditNameMd5(String editNameMd5) {
        this.editNameMd5 = editNameMd5;
    }

    public String getEditNameEncrypt() {
        return editNameEncrypt;
    }

    public void setEditNameEncrypt(String editNameEncrypt) {
        this.editNameEncrypt = editNameEncrypt;
    }

    public String getEditIdcard() {
        return editIdcard;
    }

    public void setEditIdcard(String editIdcard) {
        this.editIdcard = editIdcard;
    }

    public String getEditIdcardMd5() {
        return editIdcardMd5;
    }

    public void setEditIdcardMd5(String editIdcardMd5) {
        this.editIdcardMd5 = editIdcardMd5;
    }

    public String getEditIdcardEncrypt() {
        return editIdcardEncrypt;
    }

    public void setEditIdcardEncrypt(String editIdcardEncrypt) {
        this.editIdcardEncrypt = editIdcardEncrypt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getValidBegin() {
        return validBegin;
    }

    public void setValidBegin(String validBegin) {
        this.validBegin = validBegin;
    }

    public String getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(String validEnd) {
        this.validEnd = validEnd;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getIdcardFrontUrl() {
        return idcardFrontUrl;
    }

    public void setIdcardFrontUrl(String idcardFrontUrl) {
        this.idcardFrontUrl = idcardFrontUrl;
    }

    public String getIdcardBehindUrl() {
        return idcardBehindUrl;
    }

    public void setIdcardBehindUrl(String idcardBehindUrl) {
        this.idcardBehindUrl = idcardBehindUrl;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getFaceType() {
        return faceType;
    }

    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public String getDownloadPic() {
        return downloadPic;
    }

    public void setDownloadPic(String downloadPic) {
        this.downloadPic = downloadPic;
    }

    public String getFaceEnvUrl() {
        return faceEnvUrl;
    }

    public void setFaceEnvUrl(String faceEnvUrl) {
        this.faceEnvUrl = faceEnvUrl;
    }

    public String getFaceAct1Url() {
        return faceAct1Url;
    }

    public void setFaceAct1Url(String faceAct1Url) {
        this.faceAct1Url = faceAct1Url;
    }

    public String getFaceAct2Url() {
        return faceAct2Url;
    }

    public void setFaceAct2Url(String faceAct2Url) {
        this.faceAct2Url = faceAct2Url;
    }

    public String getFaceAct3Url() {
        return faceAct3Url;
    }

    public void setFaceAct3Url(String faceAct3Url) {
        this.faceAct3Url = faceAct3Url;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user_idcard
     *
     * @mbg.generated Tue Apr 16 20:35:46 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", userId=").append(userId);
        sb.append(", realName=").append(realName);
        sb.append(", realNameMd5=").append(realNameMd5);
        sb.append(", realNameEncrypt=").append(realNameEncrypt);
        sb.append(", idcard=").append(idcard);
        sb.append(", idcardMd5=").append(idcardMd5);
        sb.append(", idcardEncrypt=").append(idcardEncrypt);
        sb.append(", editName=").append(editName);
        sb.append(", editNameMd5=").append(editNameMd5);
        sb.append(", editNameEncrypt=").append(editNameEncrypt);
        sb.append(", editIdcard=").append(editIdcard);
        sb.append(", editIdcardMd5=").append(editIdcardMd5);
        sb.append(", editIdcardEncrypt=").append(editIdcardEncrypt);
        sb.append(", address=").append(address);
        sb.append(", gender=").append(gender);
        sb.append(", nation=").append(nation);
        sb.append(", validBegin=").append(validBegin);
        sb.append(", validEnd=").append(validEnd);
        sb.append(", birthday=").append(birthday);
        sb.append(", agency=").append(agency);
        sb.append(", idcardFrontUrl=").append(idcardFrontUrl);
        sb.append(", idcardBehindUrl=").append(idcardBehindUrl);
        sb.append(", faceUrl=").append(faceUrl);
        sb.append(", faceType=").append(faceType);
        sb.append(", downloadPic=").append(downloadPic);
        sb.append(", faceEnvUrl=").append(faceEnvUrl);
        sb.append(", faceAct1Url=").append(faceAct1Url);
        sb.append(", faceAct2Url=").append(faceAct2Url);
        sb.append(", faceAct3Url=").append(faceAct3Url);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append("]");
        return sb.toString();
    }
}