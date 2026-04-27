package com.shalion.district.exception;

import com.shalion.district.exception.code.ErrorCode;

import java.io.Serial;

import static com.shalion.district.exception.code.ErrorCode.CONFLICT;

public class ConflictException extends SystemException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ConflictException() {
        super(CONFLICT);
    }

    public ConflictException(String message) {
        super(message, CONFLICT);
    }

    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
