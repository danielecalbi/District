package com.shalion.district.exception;

import com.shalion.district.exception.code.ErrorCode;

import java.io.Serial;

import static com.shalion.district.exception.code.ErrorCode.NOT_FOUND;

public class NotFoundException extends SystemException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super(NOT_FOUND);
    }

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotFoundException(String message) {
        super(message, NOT_FOUND);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
