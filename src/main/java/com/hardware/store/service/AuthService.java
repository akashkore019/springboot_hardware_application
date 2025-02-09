// package com.hardware.store.service;

// import com.hardware.store.model.User;
// import com.hardware.store.repository.UserRepository;
// import com.hardware.store.security.JwtUtil;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// @RequiredArgsConstructor
// public class AuthService {
//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     public String registerUser(String username, String password, String role) {
//         if (userRepository.findByUsername(username).isPresent()) {
//             throw new RuntimeException("Username already taken");
//         }
//         User user = new User(null, username, passwordEncoder.encode(password), role);
//         userRepository.save(user);
//         return jwtUtil.generateToken(username);
//     }

//     public String authenticateUser(String username, String password) {
//         Optional<User> userOpt = userRepository.findByUsername(username);
//         if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }
//         return jwtUtil.generateToken(username);
//     }
// }
