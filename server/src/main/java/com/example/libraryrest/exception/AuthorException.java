package com.example.libraryrest.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

public class AuthorException extends RuntimeException {

    private final AuthorExceptionProfile authorExceptionProfile;

    public AuthorException(AuthorExceptionProfile authorExceptionProfile) {
        super(authorExceptionProfile.exceptionMessage);
        this.authorExceptionProfile = authorExceptionProfile;
    }

    public String getName() {
        return authorExceptionProfile.exceptionName;
    }

    public HttpStatus getResponseStatus() {
        return authorExceptionProfile.responseStatus;
    }

    @AllArgsConstructor
    public enum AuthorExceptionProfile  {
        AUTHOR_NOT_FOUND(
                "author_not_found",
                "Author not found.",
                HttpStatus.NOT_FOUND
        ),
        DUPLICATE_AUTHOR(
                "duplicate_author",
                "Author with the same name already exists.",
                HttpStatus.BAD_REQUEST
        );

        private final String exceptionName;
        private final String exceptionMessage;
        private final HttpStatus responseStatus;
    }

}
