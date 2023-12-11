package com.juan.usuarios.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserPasswordExpiredException extends AuthenticationException {
    public UserPasswordExpiredException(String message) {
        super(message);
    }
}
