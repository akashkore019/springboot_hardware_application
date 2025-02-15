package com.hardware.store.request.Users;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email or Mobile cannot be blank")
    private String identifier;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}