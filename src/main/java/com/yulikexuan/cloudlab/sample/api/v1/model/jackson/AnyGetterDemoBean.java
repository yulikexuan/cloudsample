//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.AnyGetterDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class AnyGetterDemoBean {

    @Builder.Default private long personId = 123L;

    @Builder.Default private String personName = "James Clark";

    @Singular
    private Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

}///:~