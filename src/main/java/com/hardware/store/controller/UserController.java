package com.hardware.store.controller;

import com.hardware.store.model.User;
import com.hardware.store.request.LoginRequest;
import com.hardware.store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

 @PostMapping("/register")
public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
    String response = userService.registerUser(user);

    if (response.startsWith("Error")) {
        return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok(response);
}


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.loginUser(loginRequest.getIdentifier(), loginRequest.getPassword());
        return user.map(value -> ResponseEntity.ok("Login successful! Welcome, " + value.getEmail()))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid email/mobile or password"));
    }
}
