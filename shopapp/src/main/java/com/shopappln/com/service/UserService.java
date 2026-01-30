package com.shopappln.com.service;


import com.shopappln.com.dto.LoginRequest;
import com.shopappln.com.dto.RegisterRequest;
import com.shopappln.com.entity.User;
import com.shopappln.com.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class UserService {


private final UserRepository repo;
private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


public UserService(UserRepository repo) {
this.repo = repo;
}


public boolean existsByEmail(String email) {
return repo.findByEmail(email).isPresent();
}


public User register(RegisterRequest request) {
// hash password before saving
String hashed = passwordEncoder.encode(request.getPassword());
User user = new User(request.getFullName(), request.getEmail(), hashed);
return repo.save(user);
}


public User login(LoginRequest request) {
Optional<User> opt = repo.findByEmail(request.getEmail());
if (opt.isEmpty()) return null;
User user = opt.get();


// verify password
boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
return matches ? user : null;
}
}