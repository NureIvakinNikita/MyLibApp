package com.example.libraryrest.model.dto;

import com.example.libraryrest.model.entity.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto {

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;
}
