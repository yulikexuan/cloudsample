//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.CreatorDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public class CreatorDemoBean {

    private final long personId;
    private final String name;

    /*
     * The @JsonCreator annotation tells Jackson that the JSON properties can
     * be mapped to the fields of a constructor of the POJO.
     * This is helpful when the JSON properties do not match with the names of
     * the Java object field names.
     *
     * The @JsonCreator annotation can be used for immutable objects which need
     * their initial values to be injected through constructors.
     */
    @JsonCreator
    public CreatorDemoBean(
            @JsonProperty("Id") long personId,
            @JsonProperty("Name") String name) {

        this.personId = personId;
        this.name = name;
    }

}///:~