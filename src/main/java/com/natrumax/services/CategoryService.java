package com.natrumax.services;

import com.natrumax.dto.request.CreateCategoryRequest;
import com.natrumax.dto.request.UpdateCategoryRequest;
import com.natrumax.models.Category;
import com.natrumax.repository.CategoryRepository;
import com.natrumax.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getName());
        category.setDescription(request.getDescription());
        category.setCreateDate(LocalDateTime.now());

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, UpdateCategoryRequest request) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(request.getName());
            category.setDescription(request.getDescription());
            category.setModifyDate(LocalDateTime.now());
            return categoryRepository.save(category);
        }
        return null;
    }
}
