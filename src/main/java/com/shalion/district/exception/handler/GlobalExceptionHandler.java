package com.shalion.district.exception.handler;

import com.shalion.district.exception.BadRequestException;
import com.shalion.district.exception.ConflictException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return handleStatusAndException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return handleStatusAndException(ex, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleStatusAndException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflict(ConflictException ex) {
        return handleStatusAndException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
       return handleStatusAndException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> handleStatusAndException(Exception ex, HttpStatus status) {
        return ResponseEntity.status(status).body(ex.getMessage());
    }

}
