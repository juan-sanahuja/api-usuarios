package com.juan.usuarios.controller;

import com.juan.usuarios.dto.AuthenticationResponse;
import com.juan.usuarios.dto.AutheticationRequest;
import com.juan.usuarios.dto.CreateUserResponse;
import com.juan.usuarios.dto.UserRegisterRequest;
import com.juan.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AutheticationRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

   /* @PostMapping("/logout")
    public ResponseEntity<UserDTO> logout(@RequestBody UserDTO userDTO) {
        userService.logout(userDTO);
        return ResponseEntity.ok().build();
    }*/
}
