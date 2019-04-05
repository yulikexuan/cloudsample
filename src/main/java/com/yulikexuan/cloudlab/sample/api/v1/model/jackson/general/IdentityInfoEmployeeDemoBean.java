//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.IdentityInfoEmployeeDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "personId")
public class IdentityInfoEmployeeDemoBean {

    @Builder.Default
    private long personId = 7L;

    @Builder.Default
    private String name = "James Bond";

    @Builder.Default
    private IdentityInfoManagerDemoBean manager =
            IdentityInfoManagerDemoBean.builder().build();

}///:~