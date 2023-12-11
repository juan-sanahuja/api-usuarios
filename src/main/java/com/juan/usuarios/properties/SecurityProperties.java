package com.juan.usuarios.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityProperties {

    private Authentication authentication;

    public SecurityProperties() {
        this.authentication = new Authentication();
        this.authentication.jwt = new Jwt();
    }

    @Getter
    @Setter
    public static class Authentication {
        private Jwt jwt;
    }

    @Getter
    @Setter
    public static class Jwt {
        private String secret;
        private long tokenValidityInSeconds;
    }
}
