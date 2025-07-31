package com.payingguest.app.services;

import com.payingguest.app.dtos.LoginRequestDTO;
import com.payingguest.app.dtos.RegisterRequestDTO;
import com.payingguest.app.dtos.AuthResponseDTO;

public interface UserService {

    // Unified registration method: decides internally based on userType
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);

    // Login (common for all user types)
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
}
