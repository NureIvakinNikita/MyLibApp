package com.example.libraryrest.cotroller;

import com.example.libraryrest.model.dto.CategoryDto;
import com.example.libraryrest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private CategoryService categoriesService;

    @Autowired
    public CategoryController(CategoryService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/categories")
    public Page<CategoryDto> getAllCategories(){
        return null;
    }

    @PostMapping("/categories")
    public CategoryDto addCategory(){
        return null;
    }

    @PutMapping("/categories/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return null;
    }

    @DeleteMapping("/categories/{id}")
    public Page<CategoryDto> deleteCategory(@PathVariable Long id){
        return null;
    }


}
