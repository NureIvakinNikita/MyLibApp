package com.example.libraryrest.service.impl;

import com.example.libraryrest.exception.CategoryException;
import com.example.libraryrest.model.dto.CategoryDto;
import com.example.libraryrest.model.entity.Category;
import com.example.libraryrest.repository.CategoryRepository;
import com.example.libraryrest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryDto> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(CategoryServiceImpl::convertToDto);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(CategoryException.CategoryExceptionProfile.CATEGORY_NOT_FOUND));
        return convertToDto(category);
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = convertToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(CategoryException.CategoryExceptionProfile.CATEGORY_NOT_FOUND));

        existingCategory.setName(categoryDto.getName());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return convertToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryException(CategoryException.CategoryExceptionProfile.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }

    public static CategoryDto convertToDto(Category category) {
        if (category == null) return null;

        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    public static Category convertToEntity(CategoryDto categoryDto) {
        if (categoryDto == null) return null;

        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
