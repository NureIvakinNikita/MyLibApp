package com.example.libraryrest.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {

    private Long id;

    private String token;

    private String firstName;

    private String lastName;

    private String role;
}
