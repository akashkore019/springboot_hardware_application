package com.hardware.store.service;

import com.hardware.store.model.User;
import com.hardware.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Add password hashing

    // public String registerUser(User user) {
    //     user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password before saving
    //     userRepository.save(user);
    // }

    public String registerUser(User user) {
        // Check if email or mobile already exists
        if (userRepository.findByEmailOrMobile(user.getEmail(), user.getMobile()).isPresent()) {
            return "Error: Email or Mobile number is already registered!";
        }
        // Hash the password and save new user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    
        return "User registered successfully!";
    }
    

    public Optional<User> loginUser(String identifier, String password) {
        Optional<User> user = userRepository.findByEmailOrMobile(identifier, identifier);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
}
