//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.AnySetterDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class AnySetterDemoBean {

    @Builder.Default private long personId = 123L;

    @Builder.Default private String  personName = "James Clark";

    @Builder.Default private Map<String, String> properties =
            new HashMap<String, String>();

    /*
     * The @JsonAnySetter annotation is used on setter methods of a Map field.
     * Sometimes you may find some JSON values that cannot be mapped to the
     * fields in the Java object class. In such a case,
     * the @JsonAnySetter captures the data and stores them in a Map.
     */
    @JsonAnySetter
    public void setProperties(String key, String value){
        properties.put(key, value);
    }

}///:~