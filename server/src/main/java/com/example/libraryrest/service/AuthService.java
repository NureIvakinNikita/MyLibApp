package com.example.libraryrest.service;

import com.example.libraryrest.exception.AuthException;
import com.example.libraryrest.model.request.UserRequest;
import com.example.libraryrest.model.response.LoginResponse;
import com.example.libraryrest.model.response.UserResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    LoginResponse login(Authentication auth);
    UserResponse register(UserRequest newUser) throws AuthException;
}
