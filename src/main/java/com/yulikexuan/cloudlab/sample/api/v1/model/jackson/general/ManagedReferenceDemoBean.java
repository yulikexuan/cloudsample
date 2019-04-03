//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.ManagedReferenceDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
@ToString
public class ManagedReferenceDemoBean {

    @JsonProperty("Id")
    @Builder.Default
    private long personId = 7L;

    @JsonProperty("Name")
    @Builder.Default
    private String name = "James Bond";

    @JsonProperty("backManager")
    @JsonManagedReference
    private BackReferenceDemoBean backManager;

}///:~