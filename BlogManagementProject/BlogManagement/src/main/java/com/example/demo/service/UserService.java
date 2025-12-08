package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // Create / register
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    // List all
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Find by id
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    // Find by email
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    // Update
    public User updateUser(Long id, User userDetails) {
        return userRepo.findById(id).map(user -> {
            user.setFullName(userDetails.getFullName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return userRepo.save(user);
        }).orElse(null);
    }

    // Delete
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return userRepo.findByEmail(email).isPresent();
    }
}
