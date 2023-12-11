package com.juan.usuarios.controller;

import com.juan.usuarios.dto.UserDTO;
import com.juan.usuarios.dto.UserRegisterRequest;
import com.juan.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> findByName(@PathVariable String email) {
        UserDTO userDTO = userService.findByEmail(email);
        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> update(@PathVariable String email, @RequestBody UserRegisterRequest user) {
        UserDTO updatedUser = userService.update(email, user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> delete(@PathVariable String email) {
        userService.delete(email);
        return ResponseEntity.ok().body("Usuario eliminado correctamente...!");
    }
}