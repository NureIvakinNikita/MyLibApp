package com.example.libraryrest.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

public class CategoryException extends RuntimeException {
    private final CategoryExceptionProfile categoryExceptionProfile;

    public CategoryException(CategoryExceptionProfile categoryExceptionProfile) {
        super(categoryExceptionProfile.exceptionMessage);
        this.categoryExceptionProfile = categoryExceptionProfile;
    }

    public String getName() {
        return categoryExceptionProfile.exceptionName;
    }

    public HttpStatus getResponseStatus() {
        return categoryExceptionProfile.responseStatus;
    }

    @AllArgsConstructor
    public enum CategoryExceptionProfile {
        CATEGORY_NOT_FOUND(
                "category_not_found",
                "Category not found.",
                HttpStatus.NOT_FOUND
        ),
        DUPLICATE_CATEGORY(
                "duplicate_category",
                "Category with the same name already exists.",
                HttpStatus.BAD_REQUEST
        );

        private final String exceptionName;
        private final String exceptionMessage;
        private final HttpStatus responseStatus;
    }
}
