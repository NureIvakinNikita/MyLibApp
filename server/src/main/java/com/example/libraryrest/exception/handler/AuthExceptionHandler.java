package com.example.libraryrest.exception.handler;

import com.example.libraryrest.exception.AuthException;
import com.example.libraryrest.exception.dto.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<Object> handleAuthException(AuthException exception,
                                                      WebRequest webRequest) {
        var exceptionBody = new ExceptionResponse(exception.getName(), exception.getMessage());
        return handleExceptionInternal(exception, exceptionBody, new HttpHeaders(),
                exception.getResponseStatus(), webRequest);
    }

    @ExceptionHandler(value = AuthorException.class)
    public ResponseEntity<Object> handleAuthorException(AuthorException exception,
                                                        WebRequest webRequest) {
        var exceptionBody = new ExceptionResponse(exception.getName(), exception.getMessage());
        return handleExceptionInternal(exception, exceptionBody, new HttpHeaders(),
                exception.getResponseStatus(), webRequest);
    }

    @ExceptionHandler(value = BookException.class)
    public ResponseEntity<Object> handleBookException(BookException exception,
                                                      WebRequest webRequest) {
        var exceptionBody = new ExceptionResponse(exception.getName(), exception.getMessage());
        return handleExceptionInternal(exception, exceptionBody, new HttpHeaders(),
                exception.getResponseStatus(), webRequest);
    }

    @ExceptionHandler(value = CategoryException.class)
    public ResponseEntity<Object> handleCategoryException(CategoryException exception,
                                                          WebRequest webRequest) {
        var exceptionBody = new ExceptionResponse(exception.getName(), exception.getMessage());
        return handleExceptionInternal(exception, exceptionBody, new HttpHeaders(),
                exception.getResponseStatus(), webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        if (body == null) {
            body = new ExceptionResponse("internal_error", "An unexpected error occurred.");
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
