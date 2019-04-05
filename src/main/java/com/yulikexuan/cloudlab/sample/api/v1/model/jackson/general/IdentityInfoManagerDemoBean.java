//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.IdentityInfoManagerDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "personId")
public class IdentityInfoManagerDemoBean {

    @Builder.Default
    public long personId = 17L;

    @Builder.Default
    public String name = "Bill Gates";

    @Singular
    public List<IdentityInfoEmployeeDemoBean> employees = new ArrayList<>();

}///:~