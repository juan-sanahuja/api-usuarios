package com.juan.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "El campo name no puede ser nulo")
    private String name;
    @Email(message = "El formato del email no es correcto.", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "El password no puede ser nulo")
    private String password;
    private String id;
    @NotEmpty(message = "El teléfono es obligatorio")
    @Valid
    private List<PhoneDTO> phones;
}
