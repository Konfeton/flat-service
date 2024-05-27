package com.onkonfeton.flatservice.auth.service;

public class UserNotActiveException extends RuntimeException{
    public UserNotActiveException(String message) {
        super(message);
    }
}
