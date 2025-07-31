package com.payingguest.app.serviceimpl;

import com.payingguest.app.dtos.*;
import com.payingguest.app.entities.User;
import com.payingguest.app.enums.UserType;
import com.payingguest.app.exceptions.InvalidCredentialException;
import com.payingguest.app.exceptions.NotFoundException;
import com.payingguest.app.repositories.UserRepository;
import com.payingguest.app.security.JwtUtils;
import com.payingguest.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new InvalidCredentialException("Passwords do not match");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidCredentialException("Email already in use");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .userType(request.getUserType()) //No case conversion needed
                .isActive(true)
                .build();

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getEmail(), user.getUserType().name());

        return buildAuthResponse(user, token);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found with this email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialException("Incorrect password");
        }

        String token = jwtUtils.generateToken(user.getEmail(), user.getUserType().name());

        return buildAuthResponse(user, token);
    }

    private AuthResponseDTO buildAuthResponse(User user, String token) {
        return AuthResponseDTO.builder()
                .status(200)
                .message("Login successful")
                .timestamp(java.time.LocalDateTime.now())
                .token(token)
                .userType(user.getUserType().name().toLowerCase()) // lowercase for old format
                .isActive(user.isActive())
                .expirationTime("6 months")
                .build();
    }

}
