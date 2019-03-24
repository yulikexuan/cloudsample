//: com.yulikexuan.cloudlab.sample.domain.services.CategoryService.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return Optional.ofNullable(this.categoryRepository.findByName(name));
    }

}///:~