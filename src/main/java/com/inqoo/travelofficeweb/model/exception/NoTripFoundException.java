package com.inqoo.travelofficeweb.model.exception;

public class NoTripFoundException extends RuntimeException {
    public NoTripFoundException(String message) {
        super(message);
    }
}