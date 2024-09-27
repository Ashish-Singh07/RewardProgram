package com.charter.retailer.reward.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreateTransactionException extends RuntimeException {
    public CreateTransactionException() {}

    public CreateTransactionException(String message) {
        super(message);
    }
}
