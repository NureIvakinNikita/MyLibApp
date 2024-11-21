package com.example.libraryrest.service;

import com.example.libraryrest.model.dto.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Page<AuthorDto> getAllAuthors(Pageable pageable);
    AuthorDto getAuthorById(Long id);
    AuthorDto addAuthor(AuthorDto authorDto);
    AuthorDto updateAuthor(Long id, AuthorDto authorDto);
    void deleteAuthor(Long id);
}
