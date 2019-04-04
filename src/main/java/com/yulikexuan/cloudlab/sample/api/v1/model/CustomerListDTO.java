//: com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import lombok.*;

import java.util.List;


@Data
@ToString
@NoArgsConstructor
@Builder @AllArgsConstructor
public class CustomerListDTO {

    @Singular
    private List<CustomerDTO> customers;

}///:~