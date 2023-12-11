package com.juan.usuarios.properties;

import com.juan.usuarios.configuration.RegexProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "api", ignoreUnknownFields = false)
@Configuration
@Getter
@Setter
public class ApiProperties {

    private SecurityProperties security;
    private RegexProperties regex;
}

