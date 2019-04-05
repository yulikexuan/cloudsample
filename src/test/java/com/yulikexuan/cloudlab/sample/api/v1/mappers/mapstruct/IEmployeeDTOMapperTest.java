//: com.yulikexuan.cloudlab.sample.api.v1.mappers.mapstruct.IEmployeeDTOMapperTest.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers.mapstruct;


import com.yulikexuan.cloudlab.sample.api.v1.model.mapstruct.EmployeeDTO;
import com.yulikexuan.cloudlab.sample.domain.model.mapstruct.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class IEmployeeDTOMapperTest {

    private IEmployeeDTOMapper mapper;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    private long id;
    private String name;

    @BeforeEach
    void setUp() {
        this.mapper = IEmployeeDTOMapper.INSTANCE;
        this.id = 17L;
        this.name = "Bill Gates";
    }

    @DisplayName("Able to map employee to DTO - ")
    @Test
    void testMapToDTO() {

        // Given
        Employee employee = Employee.builder().id(this.id).name(this.name)
                .build();

        // When
        EmployeeDTO dto = this.mapper.mapToEmployeeDTO(employee);

        // Then
        assertThat(dto.getEmployeeId())
                .as("Employee's ID should be %d", this.id)
                .isEqualTo(this.id);
        assertThat(dto.getEmployeeName())
                .as("Employee's name should be %s", this.name)
                .isEqualTo(this.name);
    }

    @DisplayName("Able to map employee DTO to employee - ")
    @Test
    void testMapToEmployee() {

        // Given
        EmployeeDTO dto = EmployeeDTO.builder().employeeId(this.id)
                .employeeName(this.name)
                .build();

        // When
        Employee employee = this.mapper.mapToEmployee(dto);

        // Then
        assertThat(employee.getId())
                .as("Employee's ID should be %d", this.id)
                .isEqualTo(this.id);
        assertThat(employee.getName())
                .as("Employee's name should be %s", this.name)
                .isEqualTo(this.name);
    }

}///:~