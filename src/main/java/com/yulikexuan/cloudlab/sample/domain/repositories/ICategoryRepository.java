//: com.yulikexuan.cloudlab.sample.domain.repositories.ICategoryRepository.java


package com.yulikexuan.cloudlab.sample.domain.repositories;


import com.yulikexuan.cloudlab.sample.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}///:~