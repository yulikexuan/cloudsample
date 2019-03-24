//: com.yulikexuan.cloudlab.sample.domain.services.ICategoryService.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.domain.model.Category;

import java.util.List;
import java.util.Optional;


public interface ICategoryService {

    List<Category> getAllCategories();

    Optional<Category> getCategoryByName(String name);

}///:~