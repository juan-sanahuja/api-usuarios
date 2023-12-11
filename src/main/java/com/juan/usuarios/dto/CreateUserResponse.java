package com.juan.usuarios.dto;

import com.juan.usuarios.model.Phone;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;

    private LocalDateTime created;
    private LocalDateTime modified;

    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
}
