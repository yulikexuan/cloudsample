//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.IgnorePropertiesDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id", "gender"})
public class IgnorePropertiesDemoBean {

    private Long id;
    private String name;
    private Gender gender;

}///:~