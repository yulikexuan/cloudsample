//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CategoryController.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICategoryMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.CategoryDTO;
import com.yulikexuan.cloudlab.sample.api.v1.model.CategoryListDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getallCatetories() {
        List<Category> categories = this.categoryService.getAllCategories();
        return CategoryListDTO.mapCategoryListToCategoryListDTO(categories);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable String name) {
        return this.categoryService.getCategoryByName(name)
                .map(ICategoryMapper.INSTANCE::categoryToCategoryDTO)
                .get();
    }

}///:~