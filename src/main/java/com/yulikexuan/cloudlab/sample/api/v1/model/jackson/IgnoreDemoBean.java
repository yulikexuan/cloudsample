//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.IgnoreDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class IgnoreDemoBean {

    static final String NAME = "James Clark";
    static final long PERSON_ID = 7L;

    @JsonIgnore
    private long personId = PERSON_ID;
    private String name = NAME;

}///:~