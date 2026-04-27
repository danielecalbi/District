package com.shalion.district.exception;

import com.shalion.district.exception.code.ErrorCode;

import java.io.Serial;

import static com.shalion.district.exception.code.ErrorCode.INTERNAL_SERVER_ERROR;

public class SystemException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public static SystemException wrap(Throwable exception, ErrorCode errorCode) {
        if (exception instanceof SystemException se) {
            if (errorCode != null && errorCode != se.getErrorCode()) {
                return new SystemException(exception.getMessage(), exception, errorCode);
            }
            return se;
        } else {
            return new SystemException(exception.getMessage(), exception, errorCode);
        }
    }

    public static SystemException wrap(Throwable exception) {
        return wrap(exception, null);
    }

    private final ErrorCode errorCode;

    public SystemException() {
        this.errorCode = INTERNAL_SERVER_ERROR;
    }

    public SystemException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public SystemException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SystemException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SystemException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
