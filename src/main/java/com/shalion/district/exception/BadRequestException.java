package com.shalion.district.exception;

import com.shalion.district.exception.code.ErrorCode;

import java.io.Serial;

import static com.shalion.district.exception.code.ErrorCode.BAD_REQUEST;

public class BadRequestException extends SystemException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super(BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message, BAD_REQUEST);
    }

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
