//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.IgnoreTypeDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder @AllArgsConstructor
public class IgnoreTypeDemoBean {

    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @JsonIgnoreType public static class Address {
        @Builder.Default private String doorNumber = "1852";
        @Builder.Default private String streetName = "Cote Vertu";
        @Builder.Default private String pinCode = "H4M 2W3";
        @Builder.Default private String city = "Toronto";
    }

    @Builder.Default
    private long personId = 7L;

    @Builder.Default
    private String name = "James Clark";

    @Builder.Default
    private Address address = Address.builder().build();

}///:~