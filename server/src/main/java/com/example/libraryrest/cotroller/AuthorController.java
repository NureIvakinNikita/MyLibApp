package com.example.libraryrest.cotroller;

import com.example.libraryrest.model.dto.AuthorDto;
import com.example.libraryrest.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public Page<AuthorDto> getAllAuthors(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "name") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return authorService.getAllAuthors(pageable);
    }

    @GetMapping("/authors/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/authors")
    public AuthorDto addAuthor(@RequestBody @Valid AuthorDto authorDto) {
        return authorService.addAuthor(authorDto);
    }

    @PutMapping("/authors/{id}")
    public AuthorDto updateAuthorById(@PathVariable Long id, @RequestBody @Valid AuthorDto authorDto) {
        return authorService.updateAuthor(id, authorDto);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
