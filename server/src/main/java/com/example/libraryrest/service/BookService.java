package com.example.libraryrest.service;

import com.example.libraryrest.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookDto> getAllBooks(Pageable pageable);
    BookDto getBookById(Long id);
    BookDto addBook(BookDto bookDto);
    BookDto updateBook(Long id, BookDto bookDto);
    void deleteBook(Long id);
}
