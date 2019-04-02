//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.PropertyDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class PropertyDemoBean {

    @JsonProperty("UserName")
    @Builder.Default
    private String username = "jamesbond";

    @JsonProperty("Password")
    @Builder.Default
    private String password = UUID.randomUUID().toString();

}///:~