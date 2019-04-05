//: com.yulikexuan.cloudlab.sample.api.v1.mappers.mapstruct.IDivisionMapper.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers.mapstruct;


import com.yulikexuan.cloudlab.sample.api.v1.model.mapstruct.DivisionDTO;
import com.yulikexuan.cloudlab.sample.domain.model.mapstruct.Division;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "default")
public interface IDivisionMapper {

    @Mappings({
            @Mapping(target = "divisionId", source = "division.id"),
            @Mapping(target = "divisionName", source = "division.name")
    })
    DivisionDTO toDivisionDTO(Division division);

    @Mappings({
            @Mapping(target = "id", source = "dto.divisionId"),
            @Mapping(target = "name", source = "dto.divisionName")
    })
    Division toDivision(DivisionDTO dto);

}///:~