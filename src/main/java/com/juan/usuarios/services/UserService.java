package com.juan.usuarios.services;

import com.juan.usuarios.dto.*;
import com.juan.usuarios.exceptions.EmailAlreadyInUseException;
import com.juan.usuarios.exceptions.UserNotFoundException;
import com.juan.usuarios.mapper.UserMapper;
import com.juan.usuarios.model.Token;
import com.juan.usuarios.model.TokenType;
import com.juan.usuarios.model.User;
import com.juan.usuarios.repositories.TokenRepository;
import com.juan.usuarios.repositories.UserRepository;
import com.juan.usuarios.security.JwtService;
import com.juan.usuarios.util.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordValidation passwordValidation;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenRepository tokenRepository;

    @Transactional
    public CreateUserResponse register(UserRegisterRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(e -> { throw new EmailAlreadyInUseException("El correo ya se encuentra registrado.", HttpStatus.CONFLICT.value()); });
        validatePassword(request.getPassword());
        User savedUser = userMapper.userDTOtoUser(request);
        String jwtToken = jwtService.generateToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return userMapper.userToUserResponseDTO(savedUser, jwtToken);
    }

    public List<UserDTO> getAllUsers() {
        return userMapper.usersToUsersDTO(userRepository.findAll());
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No se encuentra el ususario con email: " + email));
        return userMapper.userToUserDTO(user);
    }

    public UserDTO update(String email, UserRegisterRequest user) {
        User oldUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No se encuentra el ususario con email: " + email));

        User user1 = userMapper.userDTOtoUser(user);
        oldUser.setEmail(user1.getEmail());
        oldUser.setName(user1.getName());
        oldUser.setPhones(user1.getPhones());
        oldUser.setModified(LocalDateTime.now());
        final User updatedUser = userRepository.save(oldUser);
        return userMapper.userToUserDTO(userRepository.save(updatedUser));
    }

    public void delete(String email) {
        User user = userRepository.findByEmail(email).orElseThrow( () -> new UserNotFoundException("No se encuentra el ususario con email: " + email));
        userRepository.delete(user);
    }

   /* @Override
    public void logout(UserDTO userDTO) {
        revokeAllUserTokens(userRepository.findById(userDTO.getId()).orElseThrow());
    }*/

    public AuthenticationResponse authenticate(AutheticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró usuario con el username: " + request.getEmail()));
        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    private void validatePassword(String password) {
        passwordValidation.isValidPassword(password);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        user.setTokens(List.of(token));
        userRepository.save(user);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
