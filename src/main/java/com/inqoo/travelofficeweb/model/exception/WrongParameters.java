package com.inqoo.travelofficeweb.model.exception;

public class WrongParameters extends RuntimeException{
    public WrongParameters(String message) {
        super(message);
    }
}