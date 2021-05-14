package com.virtual.soft.axew.exception.handler;

import com.virtual.soft.axew.dto.error.ErrorDto;
import com.virtual.soft.axew.exception.DatabaseException;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFound (ResourceNotFoundException ex, HttpServletRequest request) {
        final var error = "Resource not found.";
        final HttpStatus status = HttpStatus.NOT_FOUND;
        var errorDto = new ErrorDto(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(errorDto, status);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorDto> databaseError (DatabaseException ex, HttpServletRequest request) {
        final var error = "Database error.";
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorDto = new ErrorDto(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(errorDto, status);
    }
}
