package com.juan.usuarios.mapper;

import com.juan.usuarios.dto.CreateUserResponse;
import com.juan.usuarios.dto.UserDTO;
import com.juan.usuarios.dto.UserRegisterRequest;
import com.juan.usuarios.model.Role;
import com.juan.usuarios.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User userDTOtoUser(UserRegisterRequest usuarioDTO) {
        LocalDateTime ldt = LocalDateTime.now();
        return User
                .builder()
                .id(UUID.randomUUID().toString())
                .name(usuarioDTO.getName())
                .email(usuarioDTO.getEmail())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .phones(usuarioDTO
                        .getPhones()
                        .stream()
                        .map(PhoneMapper::toEntity)
                        .collect(Collectors.toList()))
                .created(ldt)
                .role(Role.USER)
                .lastLogin(ldt)
                .build();
    }

    public CreateUserResponse userToUserResponseDTO(User user, String jwtToken) {
        return CreateUserResponse.builder()
                .id(UUID.fromString(user.getId()))
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .phones(user.getPhones())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(jwtToken)
                .isActive(user.isEnabled())
                .build();
    }

    public UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId())
                .phones(user.getPhones())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .isActive(user.isEnabled())
                .build();

    }

    public List<UserDTO> usersToUsersDTO(List<User> users) {
        return users.stream().map(this::userToUserDTO).collect(Collectors.toList());
    }




     /* public List<UserDTO> usersToUsersDTO(List<User> users) {
        return users.stream().map(this::userToUserDTO).collect(Collectors.toList());
    }*/

  /*  public UserDTO userToUserDTO(User user) {
        return UserDTO
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }*/
}
