package com.brights.bookcrewproject3.security.service;

import com.brights.bookcrewproject3.security.model.Category;
import com.brights.bookcrewproject3.security.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return CategoryRepository.findAll();
    }
}
