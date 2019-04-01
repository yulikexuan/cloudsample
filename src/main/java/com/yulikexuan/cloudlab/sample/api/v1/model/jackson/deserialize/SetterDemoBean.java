//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.SetterDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class SetterDemoBean {

    /*
     * Use JsonProperty annotation instead of JsonSetter and JsonGetter
     *
     * Use JsonSetter when wanting handle Nulls
     */
    @JsonProperty("Id")
    @Builder.Default
    private long personId = 0;

    @JsonSetter(value = "Name", nulls = Nulls.AS_EMPTY)
    private String name = "Mary Parker";

}///:~