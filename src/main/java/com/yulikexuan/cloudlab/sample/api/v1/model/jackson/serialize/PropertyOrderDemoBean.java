//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.PropertyOrderDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"email", "name", "personId"})
public class PropertyOrderDemoBean {

    @Builder.Default private long personId = 123L;
    @Builder.Default private String name = "James Clark";
    @Builder.Default private String email = "likexuan@yahoo.com";

}///:~