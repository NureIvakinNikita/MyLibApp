package com.example.libraryrest.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
}
