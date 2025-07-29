package com.natrumax.controllers;

import com.natrumax.dto.request.CreateCategoryRequest;
import com.natrumax.dto.request.UpdateCategoryRequest;
import com.natrumax.models.Category;
import com.natrumax.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found"));
    }

    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest request) {

        try {
            Category category = categoryService.createCategory(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category created with ID: " + category.getCategoryId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryRequest request) {
        Category updatedCategory = categoryService.updateCategory(id, request);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.status(404).body("Category not found");
        }
    }
}
