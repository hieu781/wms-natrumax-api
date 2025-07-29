package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateCategoryRequest;
import com.natrumax.dto.request.UpdateCategoryRequest;
import com.natrumax.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(Long id, UpdateCategoryRequest request);
}
