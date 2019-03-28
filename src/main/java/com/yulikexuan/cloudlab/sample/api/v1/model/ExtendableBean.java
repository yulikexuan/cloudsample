//: com.yulikexuan.cloudlab.sample.api.v1.model.ExtendableBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class ExtendableBean {

    private String name;
    private Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void add(String key, String value) {
        this.properties.put(key, value);
    }

}///:~