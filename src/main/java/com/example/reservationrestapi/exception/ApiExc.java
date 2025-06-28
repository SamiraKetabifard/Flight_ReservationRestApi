package com.example.reservationrestapi.exception;

public class ApiExc extends RuntimeException {
    private final ErrorInfo errorInfo;

    // ✅ Constructor for a custom error message (Default: 400 Bad Request)
    public ApiExc(String message) {
        super(message);
        this.errorInfo = new ErrorInfo(400, message);
    }
    // ✅ Constructor for detailed ErrorInfo
    public ApiExc(ErrorInfo errorInfo) {
        super(errorInfo.getErrorMessage());
        this.errorInfo = errorInfo;
    }
    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
