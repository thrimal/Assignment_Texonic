package com.example.assignment.exception;

public class UserLockedException extends RuntimeException {
    public UserLockedException(String message) {
        super(message);
    }
}
