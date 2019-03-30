//: com.yulikexuan.cloudlab.sample.api.v1.mappers.ICategoryMapper.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CategoryDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Component
@Mapper(uses = DateMapper.class, componentModel = "default")
public interface ICategoryMapper {

    ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);

}///:~