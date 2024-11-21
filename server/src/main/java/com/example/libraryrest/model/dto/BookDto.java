package com.example.libraryrest.model.dto;

import com.example.libraryrest.model.entity.Author;
import com.example.libraryrest.model.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookDto {
    public BookDto(Long id, String title, String isbn, int publicationYear,
                   AuthorDto author, CategoryDto category,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.author = author;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Long id;
    private String title;
    private String isbn;
    private int publicationYear;
    private AuthorDto author;
    private CategoryDto category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
