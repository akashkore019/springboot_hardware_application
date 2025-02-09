package com.hardware.store.model;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Mobile number cannot be blank")
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    private String mobile;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles; // Stores user roles (e.g., ADMIN, USER)
}
