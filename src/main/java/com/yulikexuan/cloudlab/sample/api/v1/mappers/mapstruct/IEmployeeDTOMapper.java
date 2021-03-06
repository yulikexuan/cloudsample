//: com.yulikexuan.cloudlab.sample.api.v1.mappers.mapstruct.EmployeeDTOMapper.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers.mapstruct;


import com.yulikexuan.cloudlab.sample.api.v1.model.mapstruct.EmployeeDTO;
import com.yulikexuan.cloudlab.sample.domain.model.mapstruct.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(uses = IDivisionMapper.class, componentModel = "default")
public interface IEmployeeDTOMapper {

    IEmployeeDTOMapper INSTANCE = Mappers.getMapper(IEmployeeDTOMapper.class);
    IDivisionMapper DIVISION_MAPPER = Mappers.getMapper(IDivisionMapper.class);

    @Mappings({
            @Mapping(target = "employeeId", source = "employee.id"),
            @Mapping(target = "employeeName", source = "employee.name"),
            @Mapping(target = "divisionDTO", source = "employee.division")
    })
    EmployeeDTO mapToEmployeeDTO(Employee employee);

    @Mappings({
            @Mapping(target = "id", source = "dto.employeeId"),
            @Mapping(target = "name", source = "dto.employeeName"),
            @Mapping(target = "division", source = "dto.divisionDTO")
    })
    Employee mapToEmployee(EmployeeDTO dto);

}///:~