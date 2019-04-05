//: com.yulikexuan.cloudlab.sample.domain.model.mapstruct.Employee.java


package com.yulikexuan.cloudlab.sample.domain.model.mapstruct;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
public class Employee {

    private long id;
    private String name;

}///:~