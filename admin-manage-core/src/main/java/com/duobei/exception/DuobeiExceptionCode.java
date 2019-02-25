package com.duobei.exception;

/**
 * declare
 *
 * @author: ritchey
 * @date: 2018/12/4
 * @time: 16:10
 * @version: v1.0
 * Description:
 */
public enum DuobeiExceptionCode {


    // SERVICE 9999
    SYSTEM_ERROR("SYSTEM_ERROR", 9999, "system error", "服务器操作错误");

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误编号
     */
    private int    errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 错误描述
     */
    private String desc;

    private DuobeiExceptionCode(String code, int errorCode, String errorMsg, String desc) {
        this.code = code;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.desc = desc;
    }

    public static DuobeiExceptionCode getByCode(String code) {
        if (code == null || "".equalsIgnoreCase(code)) {
            return null;
        }
        DuobeiExceptionCode[] errorCodes = values();

        for (DuobeiExceptionCode acsErrorCode : errorCodes) {
            if (acsErrorCode.getCode().equals(code)) {
                return acsErrorCode;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
