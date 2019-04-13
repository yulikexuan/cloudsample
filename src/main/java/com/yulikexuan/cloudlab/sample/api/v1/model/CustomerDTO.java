//: com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import lombok.*;

import javax.validation.constraints.Size;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Builder @AllArgsConstructor
public class CustomerDTO {

    @Size(min = 2)
    private String firstname;

    @Size(min = 2)
    private String lastname;

    private String customerUrl;

}///:~