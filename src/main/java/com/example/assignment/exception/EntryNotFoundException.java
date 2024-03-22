package com.example.assignment.exception;

public class EntryNotFoundException extends RuntimeException {
    public EntryNotFoundException(String message) {
        super(message);
    }
}
