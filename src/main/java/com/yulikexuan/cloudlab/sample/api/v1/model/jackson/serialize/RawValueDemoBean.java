//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.RawValueDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.annotation.JsonRawValue;


public class RawValueDemoBean {

    public long personId = 0;

    public String  name = "James Clark";

    /**
     * Marker annotation that indicates that the annotated method
     * or field should be serialized by including literal String value
     * of the property as is, without quoting of characters.
     * This can be useful for injecting values already serialized in JSON or
     * passing javascript function definitions from server to a javascript client.
     *
     * Warning: the resulting JSON stream may be invalid depending on your input
     * value.
     *
     * After serializing, the address in json should be:
     *   "address": {"doorNumber": 1234, "street": "phase-1", "city": "New York"}
     * Other than:
     *   "{\"doorNumber\": 1234, \"street\": \"phase-1\", \"city\": \"New York\"}";
     */
    @JsonRawValue
    public String address = "{\"doorNumber\": 1234, \"street\": \"phase-1\", " +
            "\"city\": \"New York\"}";

}///:~