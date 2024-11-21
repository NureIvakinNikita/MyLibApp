package com.example.libraryrest.service.impl;

import com.example.libraryrest.exception.BookException;
import com.example.libraryrest.model.dto.BookDto;
import com.example.libraryrest.model.entity.Book;
import com.example.libraryrest.repository.BookRepository;
import com.example.libraryrest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(this::convertToDto);
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException(BookException.BookExceptionProfile.BOOK_NOT_FOUND));
        return convertToDto(book);
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookException(BookException.BookExceptionProfile.BOOK_NOT_FOUND));

        existingBook.setTitle(bookDto.getTitle());
        existingBook.setIsbn(bookDto.getIsbn());
        existingBook.setPublicationYear(bookDto.getPublicationYear());
        existingBook.setAuthor(AuthorServiceImpl.convertToEntity(bookDto.getAuthor())); // Конверсія
        existingBook.setCategory(CategoryServiceImpl.convertToEntity(bookDto.getCategory())); // Конверсія

        Book updatedBook = bookRepository.save(existingBook);
        return convertToDto(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookException(BookException.BookExceptionProfile.BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(id);
    }

    // Допоміжні методи
    private BookDto convertToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                AuthorServiceImpl.convertToDto(book.getAuthor()),
                CategoryServiceImpl.convertToDto(book.getCategory())
        );
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId() != null ? bookDto.getId() : 0);
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setAuthor(AuthorServiceImpl.convertToEntity(bookDto.getAuthor()));
        book.setCategory(CategoryServiceImpl.convertToEntity(bookDto.getCategory()));
        return book;
    }

}
