package com.example.reservationrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
public class ApiResExc<T> {
    private boolean success;
    private T data;
    private ErrorInfo error;

    public ApiResExc(T data) {
        this.success = true;
        this.data = data;
        this.error = null;
    }

    public ApiResExc(ErrorInfo error) {
        this.success = false;
        this.data = null;
        this.error = error;
    }

}
