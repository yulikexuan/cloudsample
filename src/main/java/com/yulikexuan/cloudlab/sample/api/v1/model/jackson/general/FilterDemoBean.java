//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.FilterDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
@JsonFilter("customFilter")
public class FilterDemoBean {

    @Builder.Default
    private long personId = 17L;

    @Builder.Default
    private String name = "James Bond";

    @Builder.Default
    private String gender = "male";

}///:~