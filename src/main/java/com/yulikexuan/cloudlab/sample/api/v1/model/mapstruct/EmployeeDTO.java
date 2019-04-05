//: com.yulikexuan.cloudlab.sample.api.v1.model.mapstruct.EmployeeDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model.mapstruct;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
public class EmployeeDTO {

    private long employeeId;
    private String employeeName;

}///:~