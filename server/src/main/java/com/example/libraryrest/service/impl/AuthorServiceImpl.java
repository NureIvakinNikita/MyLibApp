package com.example.libraryrest.service.impl;

import com.example.libraryrest.exception.AuthorException;
import com.example.libraryrest.model.dto.AuthorDto;
import com.example.libraryrest.model.entity.Author;
import com.example.libraryrest.repository.AuthorRepository;
import com.example.libraryrest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Cacheable(value = "authors", key = "#pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<AuthorDto> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(AuthorServiceImpl::convertToDto);
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorException(AuthorException.AuthorExceptionProfile.AUTHOR_NOT_FOUND));
        return convertToDto(author);
    }

    @Override
    @CacheEvict(value = "authors", allEntries = true)
    public AuthorDto addAuthor(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        return convertToDto(savedAuthor);
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorException(AuthorException.AuthorExceptionProfile.AUTHOR_NOT_FOUND));

        existingAuthor.setName(authorDto.getName());
        existingAuthor.setDateOfBirth(authorDto.getDateOfBirth());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return convertToDto(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorException(AuthorException.AuthorExceptionProfile.AUTHOR_NOT_FOUND);
        }
        authorRepository.deleteById(id);
    }

    // Допоміжні методи
    public static AuthorDto convertToDto(Author author) {
        if (author == null) return null;

        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getDateOfBirth()
        );
    }

    public static Author convertToEntity(AuthorDto authorDto) {
        if (authorDto == null) return null;

        return Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .dateOfBirth(authorDto.getDateOfBirth())
                .build();
    }


}
