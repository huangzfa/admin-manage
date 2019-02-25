package com.duobei.exception;

/**
 * 业务异常类
 *
 * @author: ritchey
 * @date: 2018/12/4
 * @time: 16:08
 * @version: v1.0
 * Description:
 */
public class DuobeiException extends RuntimeException {

    private DuobeiExceptionCode errorCode;

    public DuobeiException() {
        super();
        errorCode = DuobeiExceptionCode.SYSTEM_ERROR;
    }

    public DuobeiException(String message) {
        super(message);
        errorCode = DuobeiExceptionCode.SYSTEM_ERROR;
    }

    public DuobeiException(String message, Throwable e) {
        super(message, e);
        errorCode = DuobeiExceptionCode.SYSTEM_ERROR;
    }

    public DuobeiException(Throwable e) {
        super(e);
        errorCode = DuobeiExceptionCode.SYSTEM_ERROR;
    }

    public DuobeiException(DuobeiExceptionCode CommonErrorCode) {
        super();
        errorCode = CommonErrorCode;
    }

    public DuobeiException(DuobeiExceptionCode CommonErrorCode, Throwable e) {
        super(e);
        errorCode = CommonErrorCode;
    }

    public DuobeiException(String message, DuobeiExceptionCode CommonErrorCode) {
        super(message);
        errorCode = CommonErrorCode;
    }

    public DuobeiException(String message, DuobeiExceptionCode CommonErrorCode, Throwable e) {
        super(message, e);
        errorCode = CommonErrorCode;
    }

    public DuobeiExceptionCode getErrorCode() {
        return errorCode;
    }



}
