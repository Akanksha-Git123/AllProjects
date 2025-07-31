package com.payingguest.app.controllers;

import com.payingguest.app.dtos.AuthResponseDTO;
import com.payingguest.app.dtos.LoginRequestDTO;
import com.payingguest.app.dtos.RegisterRequestDTO;
import com.payingguest.app.dtos.responses.ApiResponse;
import com.payingguest.app.dtos.responses.ResponseBuilder;
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> register(@Valid @RequestBody RegisterRequestDTO dto) {
        AuthResponseDTO authResponse = userService.register(dto);
        return ResponseEntity.ok(ResponseBuilder.success(authResponse, "Registration successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody LoginRequestDTO dto) {
        AuthResponseDTO authResponse = userService.login(dto);
        return ResponseEntity.ok(ResponseBuilder.success(authResponse, "Login successful"));
    }
}
