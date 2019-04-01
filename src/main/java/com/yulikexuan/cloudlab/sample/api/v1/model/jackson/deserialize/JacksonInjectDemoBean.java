//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.JacksonInjectDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class JacksonInjectDemoBean {

    /*
     * The @JacksonInject annotation is used to tell Jackson that particular
     * values of the deserialized object will be injected and not read from the
     * JSON string (The json string should not contain the particular field)
     *
     * In order to inject values into a field, you can use the InjectableValues
     * class.
     * You need to configure ObjectMapper to read both, the injected values
     * from injectableValues and the remaining values from the JSON string.
     */
    @JacksonInject
    private long personId = 7L;

    private String name = "James Clark";

}///:~