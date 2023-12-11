package com.juan.usuarios.util;

import com.juan.usuarios.exceptions.InvalidPasswordException;
import com.juan.usuarios.properties.ApiProperties;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class PasswordValidation {

    private final ApiProperties apiProperties;

    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(apiProperties.getRegex().getPass());
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()) {
            throw new InvalidPasswordException(apiProperties.getRegex().getPassMessage(), HttpStatus.CONFLICT.value());
        }
        return true;
    }


}
