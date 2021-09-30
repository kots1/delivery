package com.delivery.Auth;

public class NoUniqueLoginException extends Exception{
    public NoUniqueLoginException(String message) {
        super(message);
    }
}
