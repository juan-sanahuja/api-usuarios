package com.juan.usuarios.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ApiErrorDTO {
    private List<String> mensaje;
}
