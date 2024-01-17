package com.springboot.pos.service.exception;

public class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}
