package com.example.libraryrest.cotroller;

import com.example.libraryrest.model.request.UserRequest;
import com.example.libraryrest.model.response.LoginResponse;
import com.example.libraryrest.model.response.UserResponse;
import com.example.libraryrest.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(Authentication authentication) {
        return authService.login(authentication);
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRequest newUser) {
        return authService.register(newUser);
    }
}
