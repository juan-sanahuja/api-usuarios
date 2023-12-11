package com.juan.usuarios.exceptions;

public class InvalidPasswordException extends RuntimeException {

    private int code;
    public InvalidPasswordException(String message, int code) {
        super(message);
        this.code =code;
    }
}
