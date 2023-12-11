package com.juan.usuarios.datamock;

import com.juan.usuarios.dto.CreateUserResponse;
import com.juan.usuarios.dto.PhoneDTO;
import com.juan.usuarios.dto.UserRegisterRequest;
import com.juan.usuarios.model.Phone;
import com.juan.usuarios.model.Role;
import com.juan.usuarios.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserRegisterRequestMock {

    public static final String BAD_EMAIL_STRUCTURE = "juan@juan.c";
    public static LocalDateTime time(){
        return LocalDateTime.of(2023, 2,27,10,40);
    }

    public static UUID uuidStaticMock() {
        return UUID.fromString("6a464453-7eb7-4cfe-b00e-42b9426d433c");
    }

    public static UUID phoneUUIDMock() {
        return UUID.fromString("0a8cc059-8dee-4ec8-b7b8-c182d90eb7fc");
    }

    public static UserRegisterRequest userRegisterRequestMock() {
        return UserRegisterRequest
                .builder()
                .id("6a464453-7eb7-4cfe-b00e-42b9426d433c")
                .name("juan s")
                .email("juan@juan.com")
                .password("Pass$Word123")
                .phones(phonesMock())
                .build();
    }

    public static CreateUserResponse createUserResponseMock() {

        return CreateUserResponse
                .builder()
                .created(time())
                .id(uuidStaticMock())
                .modified(null)
                .isActive(Boolean.TRUE)
                .lastLogin(time())
                .build();
    }

    public static PhoneDTO phoneDtoMock() {
        return PhoneDTO
                .builder()
                .cityCode(12)
                .number(123123123)
                .countryCode(57)
                .build();
    }

    public static Phone phoneMock() {
        return Phone
                .builder()
                .phone_id(phoneUUIDMock().toString())
                .cityCode(1)
                .number(123)
                .countryCode(2)
                .build();
    }

    public static List<PhoneDTO> phonesMock() {
        return List.of(phoneDtoMock());
    }

    public static User userMock() {
        return User
                .builder()
                .id(uuidStaticMock().toString())
                .role(Role.USER)
                .name("juan")
                .phones(List.of(phoneMock()))
                .created(time())
                .lastLogin(time())
                .password("Pass$Word123")
                .email("juan@juan.com")
                .build();
    }

    public static String tokenMock() {
        return "d64bd603-03e5-46f7-aa4f-589bdad9e234";
    }
}
