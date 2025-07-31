package com.payingguest.app.controllers;

import com.payingguest.app.dtos.AuthResponseDTO;
import com.payingguest.app.dtos.LoginRequestDTO;
import com.payingguest.app.dtos.RegisterRequestDTO;
import com.payingguest.app.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Unified registration endpoint (since userType is passed in DTO)
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    // Login endpoint common for all roles
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}
