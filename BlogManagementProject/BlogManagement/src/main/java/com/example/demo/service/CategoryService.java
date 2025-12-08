package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public List<Category> getCategoriesByUserId(Long userId) {
        return categoryRepo.findByUserId(userId);
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        return categoryRepo.findById(id).map(category -> {
            category.setName(categoryDetails.getName());
            return categoryRepo.save(category);
        }).orElse(null);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
