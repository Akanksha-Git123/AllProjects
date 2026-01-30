package com.shopappln.com.controller;


import com.shopappln.com.dto.AuthResponse;
import com.shopappln.com.dto.LoginRequest;
import com.shopappln.com.dto.RegisterRequest;
import com.shopappln.com.entity.User;
import com.shopappln.com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
private final UserService service;
private final Logger log = LoggerFactory.getLogger(AuthController.class);


public AuthController(UserService service) {
this.service = service;
}


@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
try {
if (req.getFullName() == null || req.getEmail() == null || req.getPassword() == null) {
return ResponseEntity.badRequest().body(new AuthResponse(null, "Missing required fields"));
}


if (service.existsByEmail(req.getEmail())) {
return ResponseEntity.status(HttpStatus.CONFLICT)
.body(new AuthResponse(null, "Email already registered"));
}


service.register(req);
return ResponseEntity.ok(new AuthResponse(null, "Registration successful"));
} catch (Exception ex) {
log.error("Error during register", ex);
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body(new AuthResponse(null, "Internal server error"));
}
}


@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest req) {
try {
if (req.getEmail() == null || req.getPassword() == null) {
return ResponseEntity.badRequest().body(new AuthResponse(null, "Missing credentials"));
}


User user = service.login(req);
if (user == null) {
return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, "Invalid email/password"));
}


// For now return a dummy token. Replace with real JWT if you want.
return ResponseEntity.ok(new AuthResponse("dummy-token", "Login successful"));
} catch (Exception ex) {
log.error("Error during login", ex);
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body(new AuthResponse(null, "Internal server error"));
}
}
}