package com.example.reservationrestapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResExc<ErrorInfo>> buildErrorResponse(HttpStatus status, int errorCode, String message) {
        ApiResExc<ErrorInfo> response = new ApiResExc<>(false, null, new ErrorInfo(errorCode, message));
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }
    @ExceptionHandler(ApiExc.class)
    public ResponseEntity<ApiResExc<ErrorInfo>> handleApiException(ApiExc ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getErrorInfo().getErrorCode(), ex.getErrorInfo().getErrorMessage());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResExc<ErrorInfo>> handleNoSuchElementException(NoSuchElementException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, 404, "Resource not found: " + ex.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResExc<ErrorInfo>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, 400, "Invalid request: " + ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResExc<ErrorInfo>> handleGlobalException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Unexpected error: " + ex.getMessage());
    }
}
