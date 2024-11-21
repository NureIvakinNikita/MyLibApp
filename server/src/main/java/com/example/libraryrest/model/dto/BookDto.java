package com.example.libraryrest.model.dto;

import com.example.libraryrest.model.entity.Author;
import com.example.libraryrest.model.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    public BookDto(Long id, String title, String isbn,
                   int publicationYear, AuthorDto author, CategoryDto category) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.author = author;
        this.category = category;
    }

    private Long id;
    private String title;
    private String isbn;
    private int publicationYear;
    private AuthorDto author;
    private CategoryDto category;


}
