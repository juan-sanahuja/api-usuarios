package com.juan.usuarios.services;

import com.juan.usuarios.datamock.UserRegisterRequestMock;
import com.juan.usuarios.dto.CreateUserResponse;
import com.juan.usuarios.dto.UserRegisterRequest;
import com.juan.usuarios.exceptions.EmailAlreadyInUseException;
import com.juan.usuarios.exceptions.InvalidPasswordException;
import com.juan.usuarios.mapper.UserMapper;
import com.juan.usuarios.model.User;
import com.juan.usuarios.repositories.TokenRepository;
import com.juan.usuarios.repositories.UserRepository;
import com.juan.usuarios.security.JwtService;
import com.juan.usuarios.util.PasswordValidation;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserRegisterRequest registerRequestMock;
    private User userMock;
    private CreateUserResponse createUserResponseMock;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordValidation passwordValidation;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init(){
        CreateUserResponse createUserResponseExpected =
                CreateUserResponse
                        .builder()
                        .lastLogin(UserRegisterRequestMock.time())
                        .created(UserRegisterRequestMock.time())
                        .isActive(Boolean.TRUE)
                        .id(UserRegisterRequestMock.uuidStaticMock())
                        .token(UserRegisterRequestMock.tokenMock())
                        .build();
        this.registerRequestMock = UserRegisterRequestMock.userRegisterRequestMock();
        this.userMock = UserRegisterRequestMock.userMock();
        this.createUserResponseMock = UserRegisterRequestMock.createUserResponseMock();
    }

    @Test
    void UserRegister() {
        CreateUserResponse createUserResponseExpected =
                CreateUserResponse
                        .builder()
                        .lastLogin(UserRegisterRequestMock.time())
                        .created(UserRegisterRequestMock.time())
                        .isActive(Boolean.TRUE)
                        .id(UserRegisterRequestMock.uuidStaticMock())
                        .token(UserRegisterRequestMock.tokenMock())
                        .build();

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(passwordValidation.isValidPassword(any())).thenReturn(true);
        when(userMapper.userDTOtoUser(any())).thenReturn(UserRegisterRequestMock.userMock());
        when(jwtService.generateToken(any())).thenReturn(UserRegisterRequestMock.uuidStaticMock().toString());
        when(userRepository.save(any())).thenReturn(userMock);
        when(userMapper.userToUserResponseDTO(any(), any())).thenReturn(createUserResponseMock);

        CreateUserResponse createUserResponse = userService.register(registerRequestMock);

        assertEquals(createUserResponse.getId(), createUserResponseExpected.getId());
    }

    @Test
    void emailAlreadyExist() {
        String emailExpected = "juan@juan.com";
        when(userRepository.findByEmail(any())).thenThrow(EmailAlreadyInUseException.class);
        EmailAlreadyInUseException emailAlreadyInUseException = assertThrows(EmailAlreadyInUseException.class, () -> {
            userService.register(registerRequestMock);
        });
       assertEquals(EmailAlreadyInUseException.class, emailAlreadyInUseException.getClass());
    }


    @Test
    void passwordHasInvalidStructure() {
        when(passwordValidation.isValidPassword(any())).thenThrow(InvalidPasswordException.class);

        InvalidPasswordException invalidPasswordException = assertThrows(InvalidPasswordException.class, () -> {
            userService.register(registerRequestMock);
        });
        assertEquals(InvalidPasswordException.class, invalidPasswordException.getClass());

    }

}
