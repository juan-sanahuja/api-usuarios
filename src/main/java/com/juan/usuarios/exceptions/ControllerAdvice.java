package com.juan.usuarios.exceptions;

import com.juan.usuarios.exceptions.ApiErrorDTO;
import com.juan.usuarios.exceptions.EmailAlreadyInUseException;
import com.juan.usuarios.exceptions.InvalidPasswordException;
import com.juan.usuarios.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = EmailAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiErrorDTO> conflictExceptionHandler(EmailAlreadyInUseException ex) {
        return new ResponseEntity<ApiErrorDTO>(ApiErrorDTO.builder().mensaje(List.of(ex.getMessage())).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorDTO> userNotFoundExceptionHandler(UserNotFoundException ex) {
        return new ResponseEntity<ApiErrorDTO>(ApiErrorDTO.builder().mensaje(List.of(ex.getMessage())).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorDTO> notValidException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        ApiErrorDTO apiErrorDTO = ApiErrorDTO
                .builder()
                .mensaje(errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
                .build();
        return new ResponseEntity<ApiErrorDTO>(ApiErrorDTO.builder().mensaje(apiErrorDTO.getMensaje()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiErrorDTO> passwordExceptionHandler(InvalidPasswordException ex) {
        return new ResponseEntity<ApiErrorDTO>(ApiErrorDTO.builder().mensaje(List.of(ex.getMessage())).build(), HttpStatus.CONFLICT);
    }
}
