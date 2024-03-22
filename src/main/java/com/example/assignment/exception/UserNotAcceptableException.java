package com.example.assignment.exception;

public class UserNotAcceptableException extends RuntimeException {
    public UserNotAcceptableException(String message) {
        super(message);
    }
}
