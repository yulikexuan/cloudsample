//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.GetterDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;


@Setter
@Builder @AllArgsConstructor
public class GetterDemoBean {

    private long personId = 123L;
    private String personName = "James Clark";

    /**
     * Marker annotation that can be used to define a non-static, no-argument
     * value-returning (non-void) method to be used as a "getter" for a logical
     * property.
     * It can be used as an alternative to more general JsonProperty annotation
     * (which is the recommended choice in general case).
     *
     * Getter means that when serializing Object instance of class that has
     * this method (possibly inherited from a super class), a call is made
     * through the method, and return value will be serialized as value of
     * the property.
     *
     *
     * value Defines name of the logical property this method is used to access
     * ("get"); empty String means that name should be derived from the
     * underlying method (using standard Bean name detection rules)
     *
     */
    @JsonGetter(value = "Id")
    public long getPersonId() {
        return personId;
    }

    @JsonGetter(value = "Name")
    public String getPersonName() {
        return this.personName;
    }

}///:~