package com.virtual.soft.axew.exception.handler;

import com.virtual.soft.axew.dto.error.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exceptionHandler (Exception exception, HttpServletRequest request) {
        if (isAccessDenied(exception)) {
            var dto = makeAccessDeniedError(request, exception);
            return new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);
        } else {
            var dto = makeInternalError(request, exception);
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        var dto = makeBadRequestError(servletRequest, status, ex);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    private ErrorDto makeBadRequestError (HttpServletRequest request, HttpStatus status, Exception exception) {
        final var message = "BAD REQUEST!! Check your request and try again";
        return new ErrorDto(Instant.now(), status.value(), message, exception.getMessage(), request.getRequestURI());
    }

    private ErrorDto makeAccessDeniedError (HttpServletRequest request, Exception exception) {
        final var message = "FORBIDDEN!! Sorry, you are not authorized to access this feature";
        final HttpStatus status = HttpStatus.FORBIDDEN;
        return new ErrorDto(Instant.now(), status.value(), message, exception.getMessage(), request.getRequestURI());
    }

    private ErrorDto makeInternalError (HttpServletRequest request, Exception exception) {
        final var message = "INTERNAL ERROR!! Sorry, something went wrong. Please, try again";
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ErrorDto(Instant.now(), status.value(), message, exception.getMessage(), request.getRequestURI());
    }

    private boolean isAccessDenied (Exception exception) {
        return exception instanceof AccessDeniedException;
    }

}
