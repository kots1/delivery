package com.delivery.Auth;

public class IllegalPasswordException extends Throwable {
    public IllegalPasswordException(String message) {
        super(message);
    }
}
