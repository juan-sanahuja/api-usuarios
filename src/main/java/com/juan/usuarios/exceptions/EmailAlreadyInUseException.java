package com.juan.usuarios.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {

    private int code;

    public EmailAlreadyInUseException(String message, int code) {
        super(message);
        this.code = code;
    }
}
