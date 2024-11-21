package com.example.libraryrest.service.impl;

import com.example.libraryrest.exception.AuthException;
import com.example.libraryrest.model.entity.User;
import com.example.libraryrest.model.enums.Role;
import com.example.libraryrest.model.request.UserRequest;
import com.example.libraryrest.model.response.LoginResponse;
import com.example.libraryrest.model.response.UserResponse;
import com.example.libraryrest.repository.UserRepository;
import com.example.libraryrest.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtEncoder jwtEncoder;

    @Override
    public LoginResponse login(Authentication authentication) {
        return getLoginResponse(authentication.getName());

    }

    @Override
    public UserResponse register(UserRequest newUser) throws AuthException {
        if (userRepository.existsByEmailIgnoreCase(newUser.getEmail())) {
            throw new AuthException(AuthException.AuthExceptionProfile.EMAIL_OCCUPIED);
        }

        User user = User.builder()
                .email(newUser.getEmail())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(Role.CUSTOM_USER)
                .build();

        return new UserResponse(userRepository.save(user));
    }

    private LoginResponse getLoginResponse(String email) {
        var user = userRepository.findByEmailIgnoreCase(email).get();
        var now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .subject(email)
                .claim("id", user.getId())
                .claim("role", user.getRole().name().toLowerCase())
                .build();

        return LoginResponse.builder()
                .id(user.getId())
                .token(jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name().toLowerCase())
                .build();
    }
}
