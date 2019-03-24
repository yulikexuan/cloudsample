//: com.yulikexuan.cloudlab.sample.bootstrap.DefaultLoader.java


package com.yulikexuan.cloudlab.sample.bootstrap;


import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DefaultLoader implements CommandLineRunner {

	private final ICategoryRepository categoryRepository;

	@Autowired
	public DefaultLoader(ICategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Category fruits = new Category();
		fruits.setName("Fruits");

		Category dried = new Category();
		dried.setName("Dried");

		Category fresh = new Category();
		fresh.setName("Fresh");

		Category exotic = new Category();
		exotic.setName("Exotic");

		Category nuts = new Category();
		nuts.setName("Nuts");

		this.categoryRepository.save(fruits);
		this.categoryRepository.save(dried);
		this.categoryRepository.save(fresh);
		this.categoryRepository.save(exotic);
		this.categoryRepository.save(nuts);

		log.info(">>>>>>> {} categories Loaded. ", this.categoryRepository.count());
	}

}///:~