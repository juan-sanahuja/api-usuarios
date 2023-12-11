package com.juan.usuarios.security;

import lombok.Data;

@Data
public class ApiAuthenticationCredentials {
    private String email;
    private String password;
}
