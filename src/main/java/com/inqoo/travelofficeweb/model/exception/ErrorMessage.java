package com.inqoo.travelofficeweb.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private String message; // opis tekstowy błędu
    private Integer httpResponseCode; // kod HTTP
}