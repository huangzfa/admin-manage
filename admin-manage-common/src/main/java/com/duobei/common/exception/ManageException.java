package com.duobei.common.exception;

/**
 * 业务异常类
 *
 * @author: ritchey
 * @date: 2018/12/4
 * @time: 16:08
 * @version: v1.0
 * Description:
 */
public class ManageException extends RuntimeException {

    private ManageExceptionCode errorCode;

    public ManageException() {
        super();
        errorCode = ManageExceptionCode.SYSTEM_ERROR;
    }

    public ManageException(String message) {
        super(message);
        errorCode = ManageExceptionCode.SYSTEM_ERROR;
    }

    public ManageException(String message, Throwable e) {
        super(message, e);
        errorCode = ManageExceptionCode.SYSTEM_ERROR;
    }

    public ManageException(Throwable e) {
        super(e);
        errorCode = ManageExceptionCode.SYSTEM_ERROR;
    }

    public ManageException(ManageExceptionCode CommonErrorCode) {
        super();
        errorCode = CommonErrorCode;
    }

    public ManageException(ManageExceptionCode CommonErrorCode, Throwable e) {
        super(e);
        errorCode = CommonErrorCode;
    }

    public ManageException(String message, ManageExceptionCode CommonErrorCode) {
        super(message);
        errorCode = CommonErrorCode;
    }

    public ManageException(String message, ManageExceptionCode CommonErrorCode, Throwable e) {
        super(message, e);
        errorCode = CommonErrorCode;
    }

    public ManageExceptionCode getErrorCode() {
        return errorCode;
    }



}
