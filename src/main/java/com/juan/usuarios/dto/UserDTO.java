package com.juan.usuarios.dto;

import com.juan.usuarios.model.Phone;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

        private String id;
        private String name;
        private String email;
        private LocalDateTime created;
        private LocalDateTime modified;
        private List<Phone> phones;
        private LocalDateTime lastLogin;
        private boolean isActive;

}
