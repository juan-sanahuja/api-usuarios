package com.juan.usuarios.exceptions;

import org.springframework.security.core.AuthenticationException;

public class EmailNotValidException extends AuthenticationException {
    public EmailNotValidException(String msg) {
        super(msg);
    }
}
