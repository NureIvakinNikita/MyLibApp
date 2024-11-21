package com.example.libraryrest.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

public class BookException extends RuntimeException {
    private final BookExceptionProfile bookExceptionProfile;

    public BookException(BookExceptionProfile bookExceptionProfile) {
        super(bookExceptionProfile.exceptionMessage);
        this.bookExceptionProfile = bookExceptionProfile;
    }

    public String getName() {
        return bookExceptionProfile.exceptionName;
    }

    public HttpStatus getResponseStatus() {
        return bookExceptionProfile.responseStatus;
    }

    @AllArgsConstructor
    public enum BookExceptionProfile {
        BOOK_NOT_FOUND(
                "book_not_found",
                "Book not found.",
                HttpStatus.NOT_FOUND
        ),
        DUPLICATE_BOOK(
                "duplicate_book",
                "Book with the same ISBN already exists.",
                HttpStatus.BAD_REQUEST
        );

        private final String exceptionName;
        private final String exceptionMessage;
        private final HttpStatus responseStatus;
    }
}
