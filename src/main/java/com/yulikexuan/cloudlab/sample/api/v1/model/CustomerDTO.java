//: com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Builder @AllArgsConstructor
public class CustomerDTO {

    private String firstname;
    private String lastname;
    private String customerUrl;

}///:~