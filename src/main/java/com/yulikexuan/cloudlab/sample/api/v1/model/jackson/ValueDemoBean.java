//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.ValueDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
@AllArgsConstructor
public class ValueDemoBean {

    /**
     * JsonProperty:
     * Marker annotation that can be used to define a non-static
     * method as a "setter" or "getter" for a logical property
     * (depending on its signature), or non-static object field
     * to be used (serialized, deserialized) as a logical property.
     *
     * Default value ("") indicates that the field name is used
     * as the property name without any modifications, but it
     * can be specified to non-empty value to specify different
     * name. Property name refers to name used externally, as
     * the field name in JSON objects.
     *
     * Starting with Jackson 2.6 this annotation may also be
     * used to change serialization of <code>Enum</code> like so:
     * <pre>
     *     public enum MyEnum {
     *         {@literal @JsonProperty}("theFirstValue") THE_FIRST_VALUE,
     *         {@literal @JsonProperty}("another_value") ANOTHER_VALUE;
     *     }
     * </pre>
     * as an alternative to using {@link JsonValue} annotation.
     */
    @JsonProperty("PersonId")
    @Builder.Default private long personId = 123L;

    @JsonProperty("Name")
    @Builder.Default private String name = "James Clark";

    /*
     * The JsonValue annotation is used at the method level.
     * This annotation tells Jackson to use this method to generate the JSON
     * string from the Java object.
     */
    @JsonValue
    public String toJson() {
        return this.name + "," + this.personId + "," + this.toString();
    }

    @Override
    public String toString() {
        return "ValueDemoBean {" +
                "personId = " + personId +
                ", name = '" + name + '\'' + '}';
    }
}///:~