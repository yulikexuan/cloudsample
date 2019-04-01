//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.RootNameDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
@AllArgsConstructor
@JsonRootName(value = "user")
public class RootNameDemoBean {

    public long personId = 0;
    public String name = "James Clark";

}///:~