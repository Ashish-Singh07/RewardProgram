package com.charter.retailer.reward.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus error;
    private String errorDesc;
    private LocalDate date;
    
    public ApiError(HttpStatus error, String errorDesc, LocalDate date) {
        this.error = error;
        this.errorDesc = errorDesc;
        this.date = date;
    }

    public HttpStatus getError() {
        return error;
    }
    
    public void setError(HttpStatus error) {
        this.error = error;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
