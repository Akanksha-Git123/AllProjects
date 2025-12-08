package com.example.demo.Controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // allow React
public class UserController {

    @Autowired
    private UserService userService;

    // Health/test
    @GetMapping("/test")
    public String test() {
        return "UserController is up";
    }

    // Register: POST /api/users/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User saved = userService.saveUser(user);
        return ResponseEntity.ok(saved);
    }

    // Login: POST /api/users/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User login) {
        if (login.getEmail() == null || login.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        User dbUser = userService.getUserByEmail(login.getEmail());
        if (dbUser != null && dbUser.getPassword().equals(login.getPassword())) {
            return ResponseEntity.ok(dbUser);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Get all users: GET /api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
